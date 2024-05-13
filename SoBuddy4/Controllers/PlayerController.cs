using SoBuddy.Models;
using System.Collections.Generic;
using Controller = Microsoft.AspNetCore.Mvc.Controller;

namespace SoBuddy.Controllers
{

    public class PlayerController : Controller
    {
        public IEnumerable<Player> Get()
        {
            // Implement your logic to retrieve players here
            return new List<Player>();
        }
    }
}