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
    /// Interaction logic for AplicantPage.xaml
    /// </summary>
    public partial class AplicantPage : Window
    {
        private User _loggedInUser;


        public AplicantPage(User loggedInUser)
        {
            InitializeComponent();
            _loggedInUser = loggedInUser;

            WelcomeTextBlock.Text = "Welcome " + _loggedInUser.Name;
            LoadJobOffer();

        }

        private void LoadJobOffer()
        {
            using (var db = new HrDatabase())
            {
                var jobs = db.JobOffers
                .Select(j => new
                {
                    j.JobOfferId,
                    j.Title,
                    j.Description,
                    RecruiterName = j.Recruiter.Name,
                    ApplicantsCount = j.Applications.Count()
                })
                .ToList();

                JobOffersGrid.ItemsSource = jobs;
            }
        }

        private void ApplyJobButton_Click(object sender, RoutedEventArgs e)
        {
            dynamic selectedJob = JobOffersGrid.SelectedItem;

            if (selectedJob == null)
            {
                MessageBox.Show("Please select a job");
                return;
            }

            int jobOfferId = selectedJob.JobOfferId;

            ApplyJobWindow applyWindow = new ApplyJobWindow(jobOfferId, _loggedInUser);
            bool? result = applyWindow.ShowDialog();

            if (result == true)
            {
                MessageBox.Show("Application submitted.");
                LoadJobOffer();
            }
        }
        private void MyApplicationsButton_Click(object sender, RoutedEventArgs e)
        {
            ViewApplicationsWindow viewWindow = new ViewApplicationsWindow(_loggedInUser);
            viewWindow.ShowDialog();

        }

        private void LogOutButton_Click(object sender, RoutedEventArgs e)
        {
            MainWindow mainWindow = new MainWindow();
            mainWindow.Show();
            this.Close();
        }




    }
}
