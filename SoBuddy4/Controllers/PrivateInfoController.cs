using Microsoft.AspNetCore.Mvc;
using SoBuddy.Models;
using System.Collections.Generic;
using Controller = Microsoft.AspNetCore.Mvc.Controller;

namespace SoBuddy.Controllers
{
    public class PrivateInfoController : Controller
    {
        public IEnumerable<PrivateInfo> Get()
        {
            // Implement your logic to retrieve private info here
            return new List<PrivateInfo>();
        }
    }
}