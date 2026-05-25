using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using UAC_proiect.Models;

namespace UAC_proiect.Data
{
    public class HrDatabase : DbContext
    {

        public DbSet<User> Users { get; set; }
        public DbSet<JobOffer> JobOffers {  get; set; }

        public DbSet<Application> Applications  { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data Source = C:\\Users\\mrota\\source\\repos\\UAC_proiect\\UAC_proiect\\UAC_proj.db");
            //optionsBuilder.UseLazyLoadingProxies();




            
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            
            modelBuilder.Entity<JobOffer>()
                .HasOne(j => j.Recruiter)
                .WithMany(u => u.ManagedJobOffers)
                .HasForeignKey(j => j.RecruiterId)
                .OnDelete(DeleteBehavior.Restrict);

            modelBuilder.Entity<Application>()
                .HasOne(j => j.JobOffer)
                .WithMany(u => u.Applications)
                .HasForeignKey(j => j.JobOfferId)
                .OnDelete(DeleteBehavior.Cascade);
                
            modelBuilder.Entity<Application>()
                .HasOne(j => j.Applicant)
                .WithMany(u => u.Applications)
                .HasForeignKey(a => a.ApplicantId)
                .OnDelete(DeleteBehavior.Restrict);
                


        }


    }
}
