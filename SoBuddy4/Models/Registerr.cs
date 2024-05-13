using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.ComponentModel.DataAnnotations;
using System.Web.ModelBinding;



namespace SoBuddy.Models
{
    public class Registerr
    {
        [Display(Name = "ID")]
        public int ID { get; set; }

        [Required(ErrorMessage = "Please enter Username")]
        [Display(Name = "Username")]
        public string Username { get; set; }

        [Required(ErrorMessage = "Please enter your password")]
        [DataType(DataType.Password)]
        [StringLength(100, ErrorMessage = "Password \"{0}\" must have {2} characters", MinimumLength = 8)]
        public string Password { get; set; }

        [Display(Name = "confirm password")]
        [Required(ErrorMessage = "Please enter confirm password")]
        [System.ComponentModel.DataAnnotations.Compare("Password", ErrorMessage = "Confirm password doesn't match , type again my friend")]

        public string Confirmpwd { get; set; }

        [Required(ErrorMessage = "Please enter email")]
        [RegularExpression("^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", ErrorMessage = "E-mail id is not valid")]
        public string Email { get; set; }


        public Nullable<bool> Is_Deleted { get; set; }
        public List<Registerr> RegisterInfo { get; set; }


    }
}