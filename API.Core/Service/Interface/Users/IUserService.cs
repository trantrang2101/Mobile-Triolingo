using API.Core.Entity;

namespace API.Core.Service.Interface.Users
{
    public interface IUserService
    {
        Task<User> Login(User user);
        Task<bool> Regis(User user);
    }
}
