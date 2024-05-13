using Microsoft.AspNetCore.Mvc;
using SoBuddy.Models;
using System.Collections.Generic;

namespace SoBuddy.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class LobbyController : ControllerBase
    {
        [HttpGet]
        public IEnumerable<Lobby> Get()
        {
            // Implement your logic to retrieve lobbies here
            return new List<Lobby>();
        }

        [HttpPost("create")]
        public ActionResult Create(Lobby lobby)
        {
            // Implement your logic to create a new lobby here
            return Ok();
        }

        [HttpPost("addPlayer")]
        public ActionResult AddPlayer(int lobbyId, int playerId)
        {
            // Implement your logic to add a player to a lobby here
            return Ok();
        }

        [HttpPost("removePlayer")]
        public ActionResult RemovePlayer(int lobbyId, int playerId)
        {
            // Implement your logic to remove a player from a lobby here
            return Ok();
        }
    }
}