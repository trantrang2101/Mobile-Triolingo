using API.Core.Entity;
using API.Core.Service.Interface.Courses;
using API.Core.Service.Interface.Units;
using APIService.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace APIService.Controllers.Units
{
    [Route("api/[controller]")]
    [ApiController]
    public class UnitController : ControllerBase
    {
        private readonly IUnitService _unitService;
        private readonly ICourseService _courseService;
        public UnitController(IUnitService unitService, ICourseService courseService)
        {
            _unitService = unitService;
            _courseService = courseService;
        }
        [HttpGet("get/{courseId}")]
        public async Task<IActionResult> GetUnitsByCourseId(int courseId)
        {
            try
            {
                var course = await _courseService.GetCourseById(courseId);
                if (course == null)
                {
                    return NotFound();
                }
                var list = await _unitService.GetUnitsByCourseId(courseId);
                if (list.Count == 0)
                {
                    return Ok("Nothing in list");
                }
                List<UnitModel> result = new List<UnitModel>();
                foreach (var unit in list)
                {
                    result.Add(new UnitModel()
                    {
                        CourseId = courseId,
                        Id = unit.Id,
                        Name = unit.Name,
                        Description = unit.Description,
                        Note = unit.Note,
                        Order = unit.Order,
                    });
                }
                return Ok(result);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPost("add")]
        public async Task<IActionResult> AddUnit([FromBody] UnitModel unit)
        {
            try
            {
                Unit u = new Unit()
                {
                    Name = unit.Name,
                    Description = unit.Description,
                    CourseId = unit.CourseId,
                    Note = unit.Note,
                };
                if (await _unitService.AddUnit(u))
                {
                    return Ok(u);
                }
                else { return BadRequest(); }
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
    }
}
