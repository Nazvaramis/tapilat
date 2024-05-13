using System.Web.Mvc;
using SoBuddy.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Data.Entity;


namespace SoBuddy.Controllers
{
    //     !!!
    //i-am dat rename la ResgistrationController dar numele original era AccountController
    // 
    //     !!!

    public class AccountController : Controller
    {


        


        // httpget zice sa raspunda numa la raspunsuri  de tipu HTTP GET
        [HttpGet]
        public ActionResult Register()
        {


            return View();
        }

        // si pentru htp post e tot la fel , adica cand un utilizator da submit la register form , 'Register' action method o sa fie invocata cu datele din inauntru

        [HttpPost]

        public ActionResult Register(Registerr e)
        {
            
            if(Request.HttpMethod == "POST")
            {
                Registerr er = new Registerr();
                // 
                using (SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\database.mdf;Integrated Security=True;Connect Timeout=30"))






                {
                 
                   using(SqlCommand cmd = new SqlCommand("Register", con))
                    {
                        cmd.CommandType = CommandType.StoredProcedure;
                        cmd.Parameters.AddWithValue("@Username", e.Username);
                        cmd.Parameters.AddWithValue("@Password", e.Password);                      
                        cmd.Parameters.AddWithValue("@Email", e.Email);
                        cmd.Parameters.AddWithValue("@status", "INSERT");

                        con.Open();
                        ViewData["result"] = cmd.ExecuteNonQuery();
                        con.Close();



                    }


                }

            }



            return View();
        }


    }
}