using Microsoft.EntityFrameworkCore.Storage;
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
    /// Interaction logic for Register.xaml
    /// </summary>
    public partial class Register : Window
    {
        public Register()
        {
            InitializeComponent();
        }

        private void RegisterButton_Click(object sender, RoutedEventArgs e)
        {
            string name = NameTextBox.Text.Trim();
            string email = EmailTextBox.Text.Trim();
            string password = PasswordBox.Password.Trim();

            ComboBoxItem selectedroleitem = (ComboBoxItem)RoleComboBox.SelectedItem;

            string role = selectedroleitem.Content.ToString();

            if(string.IsNullOrWhiteSpace(name) || string.IsNullOrWhiteSpace(email) || string.IsNullOrWhiteSpace(password) || string.IsNullOrWhiteSpace(role))
            {
                MessageBox.Show("Complete all of the fields");
                return;
            }

            using(var db = new HrDatabase())
            {
                
                bool emailexist = db.Users.Any(u => u.Email == email);

                if (emailexist)
                {
                    MessageBox.Show("This email is already registered, try a different one");
                    return;
                }

                User newUser = new User
                {
                    Name = name,
                    Email = email,

                    Password = password,
                    Role = role

                };

                db.Users.Add(newUser);
                db.SaveChanges();


            }
            MessageBox.Show("registration successfull");

            this.Close();
        }
    }
}
