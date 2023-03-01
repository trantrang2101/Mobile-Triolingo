using API.Core.Entity;
using API.Core.Service.Interface.Exercises;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Data;

namespace APIService.Controllers.Exercises
{
    [Route("api/[controller]")]
    [ApiController]
    public class ExerciseController : Controller
    {
        private readonly IExercise _courseService;
        public ExerciseController(IExercise courseService)
        {
            _courseService = courseService;
        }
        [HttpGet]
        [Authorize]
        public IActionResult GetAllExercise()
        {
            try
            {
                return Ok(_courseService.GetAllExercise());
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPost]
        public async Task<IActionResult> AddExercise(Exercise co)
        {
            try
            {
                if (await _courseService.AddExercise(co) > 0)
                    return Ok(co);
                else return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPut]
        public async Task<IActionResult> UpdateExercise(Exercise co)
        {
            try
            {
                if (await _courseService.UpdateExercise(co))
                    return NoContent();
                else return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpDelete("id")]
        public async Task<IActionResult> DeleteCourse(int coId)
        {
            try
            {
                if (await _courseService.DeleteExercise(coId)) return Ok();
                else return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
    }
}
