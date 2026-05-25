using System;
using System.Collections.Generic;
using System.Text;

namespace UAC_proiect.Models
{
    public class Application
    {
        public int ApplicationId { get; set; }   
        public int ApplicantId { get; set; }

        public int JobOfferId {  get; set; }
        
        public int YearsOfExeperience { get; set; }
        public string CvDescription { get; set; } = "";


        public DateTime ApplicationDate { get; set; }

        public string Status { get; set; } = "";

        //Navigation propeties
        public virtual JobOffer JobOffer { get; set; } = null!;
        

        public virtual User Applicant { get; set; } = null!;

       

        //Navigation propeties


    }
}
