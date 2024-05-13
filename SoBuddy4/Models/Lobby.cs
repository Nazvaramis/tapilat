using System.Collections.Generic;

namespace SoBuddy.Models
{
    public class Lobby
    {
        public int LobbyID { get; set; }
        public int Capacity { get; set; }
        public int HostID { get; set; }
        public List<Player> Players { get; set; }
    }
}