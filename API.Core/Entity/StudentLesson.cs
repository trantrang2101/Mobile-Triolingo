using System;
using System.Collections.Generic;

namespace API.Core.Entity
{
    public class StudentLesson
    {
        public int Id { get; set; }
        public double Mark { get; set; }
        public string? Note { get; set; }
        public virtual Lesson Lesson { get; set; }
        public int LessionId { get; set; }
        public virtual StudentCourse StudentCourse { get; set; }
        public int StudentCourseId { get; set; }
    }
}
