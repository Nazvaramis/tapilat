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
using JobApplication = UAC_proiect.Models.Application;

namespace UAC_proiect
{
    public partial class ViewApplicationsWindow : Window
    {
        private User _loggedInUser;

        public ViewApplicationsWindow(User loggedInUser)
        {
            InitializeComponent();
            _loggedInUser = loggedInUser;
            LoadApplications();
        }

        private void LoadApplications()
        {
            using (var db = new HrDatabase())
            {
                var applications = db.Applications
                    .Where(a => a.ApplicantId == _loggedInUser.UserId)
                    .Select(a => new
                    {
                        a.ApplicationId,
                        JobTitle = a.JobOffer.Title,
                        a.ApplicationDate,
                        a.Status,
                        a.YearsOfExeperience,
                        a.CvDescription
                    })
                    .ToList();

                ApplicationsGrid.ItemsSource = applications;
            }
        }

        private void DeleteApplicationButton_Click(object sender, RoutedEventArgs e)
        {
            dynamic selectedApplication = ApplicationsGrid.SelectedItem;

            if (selectedApplication == null)
            {
                MessageBox.Show("Please select an application to delete.");
                return;
            }

            int applicationId = selectedApplication.ApplicationId;

            MessageBoxResult result = MessageBox.Show(
                "Are you sure you want to delete this application?",
                "Confirm Delete",
                MessageBoxButton.YesNo,
                MessageBoxImage.Warning);

            if (result == MessageBoxResult.No)
            {
                return;
            }

            using (var db = new HrDatabase())
            {
                JobApplication application = db.Applications.FirstOrDefault(a =>
                    a.ApplicationId == applicationId &&
                    a.ApplicantId == _loggedInUser.UserId);

                if (application == null)
                {
                    MessageBox.Show("Application not found or you are not allowed to delete it.");
                    return;
                }

                db.Applications.Remove(application);
                db.SaveChanges();
            }

            MessageBox.Show("Application deleted successfully.");
            LoadApplications();
        }

        private void CloseButton_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}