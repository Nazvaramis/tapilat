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
using UAC_proiect.Models;
using UAC_proiect.Data;

namespace UAC_proiect
{
    /// <summary>
    /// Interaction logic for Login.xaml
    /// </summary>
    public partial class Login : Window
    {

        public User LoggedInUser { get; set; }



        public Login()
        {
            InitializeComponent();
        }

        private void LoginButton_Click(object sender, RoutedEventArgs e)
        {
            string email = EmailTextBox.Text.Trim();
            string password = PasswordBox.Password.Trim(); 



            if (string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(password)) {

                MessageBox.Show("Complete all of the fields!");
                return;

            }

            using (var db = new HrDatabase())
            {
                User user = db.Users.FirstOrDefault(u => u.Email == email && u.Password == password);

                if (user == null)
                {

                    MessageBox.Show("invalid email or password");
                    return;
                }

                LoggedInUser = user;



            }

            MessageBox.Show("Login successfull");
            this.DialogResult = true;
            this.Close();






        }
    }
}
