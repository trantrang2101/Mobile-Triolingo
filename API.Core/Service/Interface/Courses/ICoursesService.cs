using API.Core.Entity;

namespace API.Core.Service.Interface.Courses
{
    public interface ICourseService
    {
        Task<List<Course>> GetAllCourse();
        Task<Course> GetCourseById(int? id);
        Task<int> AddNewCourse(Course Course);
        Task<bool> EditCourse(Course Course);
    }
}
