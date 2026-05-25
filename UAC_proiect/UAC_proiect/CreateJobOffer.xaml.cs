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
    /// Interaction logic for CreateJobOffer.xaml
    /// </summary>
    public partial class CreateJobOffer : Window
    {
        private User _loggedInUser;


        public CreateJobOffer(User loggedInUser)
        {
            InitializeComponent();
            _loggedInUser = loggedInUser;
        }


        private void SaveJobOffer_Click(object sender, RoutedEventArgs e)
        {
            string title = TitleTextBox.Text.Trim();
            string description = DescriptionTextBox.Text.Trim();
            string departament = DepartamentTextBox.Text.Trim();


            if (string.IsNullOrWhiteSpace(title) || string.IsNullOrWhiteSpace(description))
            {

                MessageBox.Show("Complete all of the fields");
                return;

            }

            using (var db = new HrDatabase())
            {
                JobOffer newJobOffer = new JobOffer
                {
                    Title = title,
                    Description = description,
                    RecruiterId = _loggedInUser.UserId,
                    Departament = departament

                };

                db.JobOffers.Add(newJobOffer);
                db.SaveChanges();

            }
            MessageBox.Show("job created succesfully");
            this.Close();


        }




    }
}
