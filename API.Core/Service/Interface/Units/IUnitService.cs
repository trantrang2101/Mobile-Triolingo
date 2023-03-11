using API.Core.Entity;

namespace API.Core.Service.Interface.Units
{
    public interface IUnitService
    {
        Task<List<Unit>> GetUnitsByCourseId(int? courseId);
        Task<Unit> GetById(int? unitId);
        Task<List<Unit>> GetAll();
        Task<bool> UpdateUnit(Unit unit);
        Task<bool> AddUnit(Unit unit);
    }
}
