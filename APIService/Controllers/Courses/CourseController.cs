using API.Core.Entity;
using API.Core.Service.Interface;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace APIService.Controllers.Courses
{
    [Route("api/[controller]")]
    [ApiController]
    public class CourseController : ControllerBase
    {
        private readonly ICourseService _courseService;
        public CourseController(ICourseService courseService)
        {
            _courseService = courseService;
        }
        [HttpGet]
        public IActionResult GetAllCourse()
        {
            try
            {
                return Ok(_courseService.GetAllCourses());
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPost]
        public async Task<IActionResult> AddCourse(Course co)
        {
            try
            {
                if (await _courseService.CreateCourse(co))
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
        public async Task<IActionResult> UpdateCourse(Course co)
        {
            try
            {
                if (await _courseService.UpdateCourse(co))
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
                if (await _courseService.DeleteCourse(coId)) return Ok();
                else return BadRequest();
            }
            catch(Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
    }
}
