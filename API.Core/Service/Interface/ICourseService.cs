using API.Core.Common;
using API.Core.Entity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace API.Core.Service.Interface
{
    public interface ICourseService
    {
        List<Course> GetAllCourses();
        Task<bool> CreateCourse(Course course);
        Task<bool> UpdateCourse(Course course);
        Task<bool> DeleteCourse(int courseId);
    }
}
