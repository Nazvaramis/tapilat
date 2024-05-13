namespace SoBuddy.Models
{
    public class User
    {
        public int UserID { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int Rating { get; set; }
        public bool Premium { get; set; }
        public string Email { get; set; }
        public string Role { get; set; }
    }
}