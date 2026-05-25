using System;
using System.Collections.Generic;
using System.Text;

namespace UAC_proiect.Models
{
    public class User
    {
        public int UserId { get; set; }
        public string Name { get; set; } = "";
        public string Password { get; set; } = "";
        public string Email { get; set; } = "";

        public string Role { get; set; } = "";

        // navigation propeties 
        public virtual ICollection<JobOffer> ManagedJobOffers { get; set; } = new List<JobOffer>();
        public virtual ICollection<Application> Applications { get; set; } = new List<Application>();


        // navigatin propeties

    }
}
