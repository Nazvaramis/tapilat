using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace UAC_proiect.Migrations
{
    /// <inheritdoc />
    public partial class Updae : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Departament",
                table: "JobOffers",
                type: "TEXT",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<string>(
                name: "Idk",
                table: "JobOffers",
                type: "TEXT",
                nullable: false,
                defaultValue: "");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Departament",
                table: "JobOffers");

            migrationBuilder.DropColumn(
                name: "Idk",
                table: "JobOffers");
        }
    }
}
