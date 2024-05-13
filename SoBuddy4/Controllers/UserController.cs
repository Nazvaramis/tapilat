using SoBuddy.Models;
using System.Collections.Generic;

using Microsoft.AspNetCore.Mvc;


namespace SoBuddy.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : ControllerBase
    {
        [HttpGet]
        public IEnumerable<User> Get()
        {
            // Implement your logic to retrieve users here
            return new List<User>();
        }

        [HttpPost("register")]
        public ActionResult Register(User user)
        {
            // Implement your logic to register a new user here
            return Ok();
        }

        [HttpPost("login")]
        public ActionResult Login(LoginModel loginModel)
        {
            // Implement your logic to login a user here
            return Ok();
        }

        [HttpPost("switch")]
        public ActionResult SwitchToPremium(int userId)
        {
            // Implement your logic to switch a user to premium here
            return Ok();
        }
    }
}