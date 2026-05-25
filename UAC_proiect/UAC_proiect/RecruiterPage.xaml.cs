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
    /// Interaction logic for RecruiterPage.xaml
    /// </summary>
    /// 

    public partial class RecruiterPage : Window
    {

        private User _loggedInUser;

           
        public RecruiterPage(User loggedInUser)
        {
            InitializeComponent();

            _loggedInUser = loggedInUser; 

            WelcomeTextBlock.Text ="Welcome " + _loggedInUser.Name;
            LoadJobOffer();
            

        }

        public void LoadJobOffer()
        {
            using (var db = new HrDatabase())
            {
                var jobs = db.JobOffers
                    .Where(j => j.RecruiterId == _loggedInUser.UserId)
                    .ToList();

                JobOffersGrid.ItemsSource = jobs;



            }

        }

        private void ModifyJobOfferButton_Click(object sender, RoutedEventArgs e)
        {
            JobOffer selectedJob = JobOffersGrid.SelectedItem as JobOffer;

            if (selectedJob == null)
            {
                MessageBox.Show("Please select a job offer to modify.");
                return;
            }

            ModifyJobOfferWindow modifyWindow = new ModifyJobOfferWindow(selectedJob.JobOfferId,_loggedInUser);
            bool? result = modifyWindow.ShowDialog();

            if(result == true) {
                LoadJobOffer();
            }




            
        }


        private void CreateJobButton_Click(object sender, RoutedEventArgs e)
        {
            CreateJobOffer createJobOffer = new CreateJobOffer(_loggedInUser);
            createJobOffer.ShowDialog();

            LoadJobOffer();
            

        }

        private void ViewApplicantsButton_Click(object sender, RoutedEventArgs e)
        {
            JobOffer selectedJob = JobOffersGrid.SelectedItem as JobOffer;

            if (selectedJob == null)
            {
                MessageBox.Show("Please select a job offer.");
                return;
            }

            ViewApplicantsWindow viewApplicantsWindow = new ViewApplicantsWindow(selectedJob.JobOfferId, _loggedInUser);
            viewApplicantsWindow.ShowDialog();

            LoadJobOffer();
        }


        private void LogOut_Click(object sender, RoutedEventArgs e)
        {
            _loggedInUser = null;
            MainWindow mainWindow = new MainWindow();
            mainWindow.Show();

            this.Close();

        }


    }
}
