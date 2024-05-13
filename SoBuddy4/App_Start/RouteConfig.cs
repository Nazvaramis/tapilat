using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Routing;

namespace SoBuddy
{
    public class RouteConfig
    {
        public static void RegisterRoutes(RouteCollection routes)
        {
            routes.IgnoreRoute("{resource}.axd/{*pathInfo}");


            // i-am pus o ruta sa treaca prin LoginController , nu sunt sigur ca o sa mearga , [verifica daca merge fara. mesaj ptr Eu(Sima)]
            // sa nu avem cod in plus de-am pulea

            routes.MapRoute(

                name: "Login",
                url: "login/{action}/{id}",
                defaults: new { controller = "Login", action = "LoginController", id = UrlParameter.Optional }




            );




            routes.MapRoute(
                name: "Default",
                url: "{controller}/{action}/{id}",
                defaults: new { controller = "Home", action = "HomePage", id = UrlParameter.Optional }
            );
        }
    }
}








/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * routes.MapRoute(

                name: "Login",
                url: "login/{action}/{id}",
                defaults: new {controller = "Login", action = "Login", id = UrlParameter.Optional}




            );*/







