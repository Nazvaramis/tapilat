using System;
using System.Collections.Generic;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using UAC_proiect.Data;
using UAC_proiect.Models;

namespace UAC_proiect
{
    /// <summary>
    /// Interaction logic for ApplyJobWindow.xaml
    /// </summary>
    /// 
    public partial class ApplyJobWindow : Window
    {
        private int _jobOfferId;
        private User _loggedInUser;

        public ApplyJobWindow(int jobOfferId, User loggedInUser)
        {
            InitializeComponent();
            _jobOfferId = jobOfferId;
            _loggedInUser = loggedInUser;
        }

        private void ApplyButton_Click(object sender, RoutedEventArgs e)
        {
            string cvDescription = CvDescriptionTextBox.Text.Trim();
            string experienceText = YearsOfExperienceTextBox.Text.Trim();

            if (string.IsNullOrWhiteSpace(cvDescription) || string.IsNullOrWhiteSpace(experienceText))
            {
                MessageBox.Show("Complete all fields.");
                return;
            }

            if (!int.TryParse(experienceText, out int yearsOfExperience))
            {
                MessageBox.Show("Years of experience must be a valid number.");
                return;
            }

            try
            {
                using (var db = new HrDatabase())
                {
                    UAC_proiect.Models.Application newApplication = new UAC_proiect.Models.Application
                    {
                        ApplicantId = _loggedInUser.UserId,
                        JobOfferId = _jobOfferId,
                        ApplicationDate = DateTime.Now,
                        Status = "Pending",
                        YearsOfExeperience = yearsOfExperience,
                        CvDescription = cvDescription
                    };

                    db.Applications.Add(newApplication);
                    db.SaveChanges();
                }

                MessageBox.Show("Application submitted successfully.");
                this.DialogResult = true;
                this.Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show("Failed to submit application: " + ex.Message);
            }
        }

        private void ExitButton_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
