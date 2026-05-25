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
    /// Interaction logic for ModifyJobOfferWindow.xaml
    /// </summary>
    public partial class ModifyJobOfferWindow : Window
    {
        private int _jobOfferId;
        private User _loggedInUser;


        public ModifyJobOfferWindow(int jobOfferId, User loggedInUser)
        {
            InitializeComponent();
            _jobOfferId = jobOfferId;
            _loggedInUser = loggedInUser;
            LoadJobOfferData();
            
        }

        private void LoadJobOfferData()
        {
            using (var db = new HrDatabase())
            {
                JobOffer job = db.JobOffers.FirstOrDefault(j => j.JobOfferId == _jobOfferId &&  j.RecruiterId == _loggedInUser.UserId);

                if (job == null)
                {

                    MessageBox.Show("Job offer not found");
                    this.Close();
                    return;

                }
                TitleTextBox.Text = job.Title;
                DescriptionBox.Text = job.Description;


            }




        }

        private void SubmitChanges_Click(object sender, RoutedEventArgs e)
        {

            string Title = TitleTextBox.Text;
            string Description = DescriptionBox.Text;

            if(string.IsNullOrWhiteSpace(Title) || string.IsNullOrWhiteSpace(Description))
            {
                MessageBox.Show("Complete all fields");
                return;
            }

            try
            {
                using(var db = new HrDatabase())
                {
                    JobOffer job = db.JobOffers.FirstOrDefault(j => j.JobOfferId == _jobOfferId && j.RecruiterId == _loggedInUser.UserId);

                    if( job == null)
                    {
                        MessageBox.Show("Job Offer not found");
                        return;
                    }

                    job.Title = Title;
                    job.Description = Description;

                    db.SaveChanges();
                }

                MessageBox.Show("Changed successfully");
                 
                this.DialogResult = true;
                this.Close();

            }catch(Exception ex)
            {
                MessageBox.Show("Update failed" + ex.Message);
                    
            }




        }

        private void Exit_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        private void DeletePost_Click(object sender, RoutedEventArgs e)
        {
            MessageBoxResult result = MessageBox.Show("are you sure you want to delete", "confirm delete", MessageBoxButton.YesNo, MessageBoxImage.Warning);

            if (result == MessageBoxResult.No)
            {
                return;
            }





            try
            {
                using(var db = new HrDatabase())
                {
                    JobOffer job = db.JobOffers.FirstOrDefault(j => j.JobOfferId == _jobOfferId && j.RecruiterId == _loggedInUser.UserId);


                    if(job == null)
                    {
                        MessageBox.Show("job post not found ");
                        return;
                    }

                    
                    db.JobOffers.Remove(job);
                    db.SaveChanges();

                }
                MessageBox.Show("Job post deleted successfully");
                this.DialogResult= true;
                this.Close();


            }catch(Exception ex)
            {
                MessageBox.Show("delete failed" + ex.Message);


            }





        }
    }


}
