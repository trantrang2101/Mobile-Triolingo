using API.Core.Service.Service.Courses;
using API.Core.Service.Service.Exercises;
using API.Core.Service.Interface.Courses;
using API.Core.Service.Interface.Exercises;
using API.Core.Service.Interface.Lessons;
using API.Core.Service.Interface.QnA;
using API.Core.Service.Interface.Settings;
using API.Core.Service.Interface.Units;
using API.Core.Service.Interface.Users;
using API.Core.Service.Service.Lessons;
using API.Core.Service.Service.QnA;
using API.Core.Service.Service.Settings;
using API.Core.Service.Service.Units;
using API.Core.Service.Service.Users;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
//var configuration = builder.Configuration;
//string connectionString = configuration.GetConnectionString("TriolingoConStr");
//builder.Services.AddDbContext<TriolingoDbContext>(options =>
//        options.UseSqlServer(connectionString));
#region regiter DI service
// builder.Services.AddTransient<IExercise, ExerciseService>();
// builder.Services.AddTransient<IAnswer, AnswerService>();
// builder.Services.AddTransient<IQuestion, QuestionService>();
// builder.Services.AddTransient<ICourseService, CourseService>();
// builder.Services.AddTransient<ISettingService, SettingService>();
// builder.Services.AddTransient<ILessonService, LessonService>();
// builder.Services.AddTransient<IUserService, UserService>();
// builder.Services.AddTransient<IUnitService, UnitService>();
#endregion
var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
