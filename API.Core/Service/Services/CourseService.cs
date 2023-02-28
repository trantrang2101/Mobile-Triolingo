using API.Core.Common;
using API.Core.DataAccess;
using API.Core.Entity;
using Microsoft.AspNetCore.Http;
using API.Core.Service.Interface;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace API.Core.Service.Services
{
    public class CourseService : ICourseService
    {
        private readonly TriolingoDbContext _context;
        public CourseService(TriolingoDbContext context)
        {
            _context = context;
        }
        public async Task<bool> CreateCourse(Course course)
        {
            _context.Courses.Add(course);
            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> DeleteCourse(int courseId)
        {
            var co = await _context.Courses.SingleOrDefaultAsync(x => x.Id == courseId);
            if (co != null)
            {
                _context.Courses.Remove(co);
                await _context.SaveChangesAsync();
                return true;
            }
            return false;
        }

        public List<Course> GetAllCourses()
        {
            return _context.Courses.ToList();
        }

        public async Task<bool> UpdateCourse(Course course)
        {
            var co = await _context.Courses.SingleOrDefaultAsync(x => x.Id == course.Id);
            if (co != null)
            {
                co.Description = course.Description;
                co.RateAverage = course.RateAverage;
                co.Note = course.Note;
                co.Name = course.Name;
                co.Status = course.Status;
                _context.Courses.Update(co);
                await _context.SaveChangesAsync();
                return true;
            }
            return false;
        }
        #region private method
        //private string ValidateAddCourse(Course course)
        //{
            
        //} 
        #endregion
    }
}
