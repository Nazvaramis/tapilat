using Microsoft.AspNetCore.Mvc;
using SoBuddy.Models;
using System.Collections.Generic;
using Controller = System.Web.Mvc.Controller;

namespace SoBuddy.Controllers
{
    public class FieldController : Controller
    {
        public IEnumerable<Field> Get()
        {
            // Implement your logic to retrieve fields here
            return new List<Field>();
        }
    }
}