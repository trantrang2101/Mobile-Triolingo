using API.Core.Entity;
using API.Core.Service.Interface;
using API.Core.Service.Interface.Courses;
using Microsoft.AspNetCore.Authorization;
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
        [Authorize]
        public IActionResult GetAllCourse()
        {
            try
            {
                return Ok(_courseService.GetAllCourse());
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPost]
        [Authorize(Roles ="Admin")]
        public async Task<IActionResult> AddCourse(Course co)
        {
            try
            {
                if (await _courseService.AddNewCourse(co)>0)
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
        [Authorize(Roles = "Admin")]
        public async Task<IActionResult> UpdateCourse(Course co)
        {
            try
            {
                if (await _courseService.EditCourse(co))
                    return NoContent();
                else return BadRequest();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        //[HttpDelete("id")]
        //public async Task<IActionResult> DeleteCourse(int coId)
        //{
        //    try
        //    {
        //        if (await _courseService.DeleteCourse(coId)) return Ok();
        //        else return BadRequest();
        //    }
        //    catch(Exception ex)
        //    {
        //        return BadRequest(ex.Message);
        //        throw;
        //    }
        //}
    }
}
