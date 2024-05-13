using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ComponentModel.DataAnnotations;
using System.Web.ModelBinding;


namespace SoBuddy.Models
{
    public class Loginn
    {

        [Required(ErrorMessage = "Enter Username first")]
        [Display(Name = "Username")]

        public string Username { get; set; }


        [Required(ErrorMessage = "Enter Password ")]
        [Display(Name = "Password")]

        public string Password { get; set; }

        public List<Loginn> LoginInfo { get; set; }




    }
}