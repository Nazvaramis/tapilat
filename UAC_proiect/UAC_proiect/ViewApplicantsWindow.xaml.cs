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
    /// <summary>
    /// Interaction logic for ViewApplicantsWindow.xaml
    /// </summary>
    /// 
    public partial class ViewApplicantsWindow : Window
    {
        private int _jobOfferId;
        private User _loggedInUser;

        public ViewApplicantsWindow(int jobOfferId, User loggedInUser)
        {
            InitializeComponent();
            _jobOfferId = jobOfferId;
            _loggedInUser = loggedInUser;
            LoadApplicants();
        }

        private void LoadApplicants()
        {
            using (var db = new HrDatabase())
            {
                var job = db.JobOffers.FirstOrDefault(j => j.JobOfferId == _jobOfferId && j.RecruiterId == _loggedInUser.UserId);

                if (job == null)
                {
                    MessageBox.Show("job offer not found.");
                    this.Close();
                    return;
                }

                TitleTextBlock.Text = "Applicants for: " + job.Title;

                var applicants = db.Applications
                    .Where(a => a.JobOfferId == _jobOfferId && a.JobOffer.RecruiterId == _loggedInUser.UserId)
                    .Select(a => new
                    {
                        a.ApplicationId,
                        ApplicantName = a.Applicant.Name,
                        ApplicantEmail = a.Applicant.Email,
                        a.YearsOfExeperience,
                        a.CvDescription,
                        a.ApplicationDate,
                        a.Status
                    })
                    .ToList();

                ApplicantsGrid.ItemsSource = applicants;
            }
        }

        private void AcceptButton_Click(object sender, RoutedEventArgs e)
        {
            dynamic selectedApplication = ApplicantsGrid.SelectedItem;

            if (selectedApplication == null)
            {
                MessageBox.Show("Please select an application.");
                return;
            }

            int applicationId = selectedApplication.ApplicationId;
            UpdateApplicationStatus(applicationId, "Accepted");
        }

        private void RejectButton_Click(object sender, RoutedEventArgs e)
        {
            dynamic selectedApplication = ApplicantsGrid.SelectedItem;

            if (selectedApplication == null)
            {
                MessageBox.Show("Please select an application.");
                return;
            }

            int applicationId = selectedApplication.ApplicationId;
            UpdateApplicationStatus(applicationId, "Rejected");
        }

        private void UpdateApplicationStatus(int applicationId, string newStatus)
        {
            using (var db = new HrDatabase())
            {
                JobApplication application = db.Applications.FirstOrDefault(a =>
                    a.ApplicationId == applicationId &&
                    a.JobOfferId == _jobOfferId &&
                    a.JobOffer.RecruiterId == _loggedInUser.UserId);

                if (application == null)
                {
                    MessageBox.Show("Application not found or you are not allowed to modify it.");
                    return;
                }

                application.Status = newStatus;
                db.SaveChanges();
            }

            MessageBox.Show("Application status updated to " + newStatus + ".");
            LoadApplicants();
        }

        private void CloseButton_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
