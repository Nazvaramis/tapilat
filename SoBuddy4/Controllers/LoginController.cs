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
using System.Web.Security;





namespace SoBuddy.Controllers
{
    public class LoginController : Controller
    {
        // GET: Login
        public string value = "";

        public string status;

        public ActionResult Login()
        {
            

            return View();
        }


        [HttpPost]
        public ActionResult Login(Registerr e)
        {

            String SqlCon = ConfigurationManager.ConnectionStrings["database"].ConnectionString;
            SqlConnection con = new SqlConnection(SqlCon);
            string SqlQuery = "SELECT Username, Password FROM LoginCredentials WHERE Username = @Username AND Password = @Password";

            con.Open();
            SqlCommand cmd = new SqlCommand(SqlQuery, con); ;
            //cmd.Parameters.AddWithValue("@Email", e.Email);
            cmd.Parameters.AddWithValue("@Password", e.Password);
            cmd.Parameters.AddWithValue("@Username", e.Username);
            SqlDataReader sdr = cmd.ExecuteReader(); // da execute the sql querry against the database

            if (sdr.Read())
            {
                Session["Username"] = e.Username.ToString();
                Session["Password"] = e.Password.ToString();
                return RedirectToAction("Welcome");


            }
            else
            {

                ViewData["Message"] = "User Login Details Failed, try again my friend";

            }
            if (e.Username.ToString() != null)
            {

                Session["Username"] = e.Username.ToString();
                Session["Password"] = e.Password.ToString();
                status = "1";


            }
            else
            {

                status = "3";

            }

            //inchidem conexiunea
            con.Close();


            return View();

        }






        [HttpGet]
        public ActionResult Welcome(Registerr e)
        {
            Registerr user = new Registerr();
            DataSet ds = new DataSet();

            using (SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\database.mdf;Integrated Security=True;Connect Timeout=30"))
            {

                using (SqlCommand cmd = new SqlCommand("GetUserRegistered", con))
                {
                    using (SqlCommand cnd = new SqlCommand("GUR2", con))
                    {



                        cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@Username", SqlDbType.VarChar, 30).Value = Session["Username"].ToString();

                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.Add("@Password", SqlDbType.VarChar, 30).Value = Session["Password"].ToString();

                    //open a connection with sql server
                    con.Open();

                    // SqlDataAdapter il folosim ptr. a lua date din  database si sa le punem intr-un dataTable sau dataSet

                    SqlDataAdapter sda = new SqlDataAdapter(cmd);

                    sda.Fill(ds);

                    // creeze o lista ca sa aafisam in admin pannel

                    List<Registerr> userlist = new List<Registerr>();


                    


                        for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                        {
                            Registerr Obj = new Registerr();


                            // aici iau obiectele din database
                            Obj.ID = Convert.ToInt32(ds.Tables[0].Rows[i]["ID"].ToString());
                            Obj.Username = ds.Tables[0].Rows[i]["Username"].ToString();
                            Obj.Email = ds.Tables[0].Rows[i]["Email"].ToString();





                            Obj.Password = ds.Tables[0].Rows[i]["Password"].ToString();





                            //bagam toate obiectele in lista sa fie afisate in pannel



                            userlist.Add(Obj);





                        }

                        // luam datele ca sa poate fi afisate in httpu 

                        user.RegisterInfo = userlist;


                    }
                    // inchidem conectiunea cu database

                    con.Close();





                }

            }

            return View(user);
        }

        public ActionResult Logout()
        {
            FormsAuthentication.SignOut();
            Session.Abandon();


            // ori e "homePage", "homepPage" ori e "Registerr","HomePage" , tre sa verific dupa ce dau run
            return RedirectToAction("HomePage", "HomePage");


        }

        




    }
}


/*
 * 
 * 
 * // GET: Login
        public string value = "";

        public string status;

        public ActionResult Login()
        {



            return View();
        }


        [HttpPost]
        public ActionResult Login(Registerr e)
        {
            String SqlCon = ConfigurationManager.ConnectionStrings["database"].ConnectionString;
            SqlConnection con = new SqlConnection(SqlCon);
            string SqlQuery = "SELECT Users.Email , LoginCredentials.Username,LoginCredential.Password";
            con.Open();
            SqlCommand cmd = new SqlCommand(SqlQuery, con);
            cmd.Parameters.AddWithValue("@Email", e.Email);
            cmd.Parameters.AddWithValue("@Password", e.Password);
            cmd.Parameters.AddWithValue("@Username", e.Username);
            SqlDataReader sdr = cmd.ExecuteReader(); // citeste din data base 

            if (sdr.Read())
            {
                Session["Email"] = e.Email.ToString();
                return RedirectToAction("Welcome");


            }
            else
            {

                ViewData["Message"] = "User Login Details Failed, try again my friend";

            }
            if (e.Email.ToString() != null)
            {

                Session["Email"] = e.Email.ToString();
                status = "1";


            }
            else
            {

                status = "3";

            }

            //inchidem conexiunea
            con.Close();


            return View();

        }


        // [httpget] ,trebe sa mut intr-un fisier separat ca nu am voie 2 httpgeturi aparent

        public ActionResult Welcome(Registerr e)
        {
            Registerr user = new Registerr();
            DataSet ds = new DataSet();

            using (SqlConnection con = new SqlConnection(@"Data Source=(LocalDB)\MSSQLLocalDB;AttachDbFilename=|DataDirectory|\database.mdf;Integrated Security=True;Connect Timeout=30"))
            {



            }

            return View();
        }
 * 
 * 
 * 
 */