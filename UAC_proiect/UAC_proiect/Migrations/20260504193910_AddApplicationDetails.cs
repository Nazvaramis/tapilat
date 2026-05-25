using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace UAC_proiect.Migrations
{
    /// <inheritdoc />
    public partial class AddApplicationDetails : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "CvDescription",
                table: "Applications",
                type: "TEXT",
                nullable: false,
                defaultValue: "");

            migrationBuilder.AddColumn<int>(
                name: "YearsOfExeperience",
                table: "Applications",
                type: "INTEGER",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "CvDescription",
                table: "Applications");

            migrationBuilder.DropColumn(
                name: "YearsOfExeperience",
                table: "Applications");
        }
    }
}
