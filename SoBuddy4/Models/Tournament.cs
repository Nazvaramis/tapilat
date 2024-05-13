using System;

namespace SoBuddy.Models
{
    public class Tournament
    {
        public int TournamentID { get; set; }
        public int Capacity { get; set; }
        public int LobbyID { get; set; }
        public bool Premium { get; set; }
        public DateTime TournamentDate { get; set; }
        public string Prize { get; set; }
        public int HostID { get; set; }
    }
}