using System;
using System.Collections.Generic;
using System.Text;

namespace UAC_proiect.Models
{
    public class JobOffer
    {

        public int JobOfferId { get; set; }

        public string Title { get; set; } = "";

        public string Description { get; set; } = "";

        public string Departament { get; set; } = "";
        public string Idk { get; set; } ="";

        public int RecruiterId  { get; set; }

        
        // navigation propeties 
        public virtual ICollection<Application> Applications { get; set; } = new List<Application>();

        public User Recruiter { get; set; } = null!;


        // navigation propeties 

    }
}
