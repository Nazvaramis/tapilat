using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using UAC_proiect.Models;


namespace UAC_proiect
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Register registerwindow = new Register();

            registerwindow.ShowDialog();


        }

        private void Login(object sender, RoutedEventArgs e)
        {
            Login loginwindow = new Login();

            bool? result = loginwindow.ShowDialog();

            if (result == true)
            {
                User LoggedInUser = loginwindow.LoggedInUser;

                MessageBox.Show("Welcome" + LoggedInUser.Name + " ( " + LoggedInUser.Role + ")" );

                if(LoggedInUser.Role == "Recruiter")
                {

                    RecruiterPage recruiterPage = new RecruiterPage(LoggedInUser);
                    recruiterPage.Show();
                    this.Close();


                }
                else
                {
                    AplicantPage aplicantPage = new AplicantPage(LoggedInUser);
                    aplicantPage.Show();
                    this.Close();


                }



            }

        }


    


    }
}