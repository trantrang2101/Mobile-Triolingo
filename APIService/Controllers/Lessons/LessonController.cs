using API.Core.Entity;
using API.Core.Service.Interface.Lessons;
using API.Core.Service.Interface.Units;
using APIService.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace APIService.Controllers.Lessons
{
    [Route("api/[controller]")]
    [ApiController]
    public class LessonController : ControllerBase
    {
        private readonly ILessonService _lessonService;
        private readonly IUnitService _unitService;
        public LessonController(ILessonService lessonService, IUnitService unitService)
        {
            _lessonService = lessonService;
            _unitService = unitService;
        }
        [HttpGet("get/{unitId}")]
        public async Task<IActionResult> GetLessonsByUnitId(int unitId)
        {
            try
            {
                var unit = await _unitService.GetById(unitId);
                if (unit == null)
                {
                    return NotFound();
                }
                var list = await _lessonService.getAllLessonsByUnitId(unitId);
                return Ok(list);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
                throw;
            }
        }
        [HttpPost("add")]
        public async Task<IActionResult> AddUnit([FromBody] LessonModel lesson)
        {
            try
            {
                Lesson le = new Lesson()
                {
                    Name = lesson.Name,
                    UnitId = lesson.UnitId,
                    Description = lesson.Description,
                    Note = lesson.Note,
                };
                if (await _lessonService.AddLesson(le))
                {
                    return Ok(le);
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
