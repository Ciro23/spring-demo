package it.tino.springdemo.course.controller;


import it.tino.springdemo.course.Course;
import it.tino.springdemo.course.persistence.CourseDataSource;
import it.tino.springdemo.student.Student;
import it.tino.springdemo.student.persistence.StudentDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("courses")
public class CourseController {

    CourseDataSource courseDataSource;

    public CourseController(CourseDataSource courseDataSource) {
        this.courseDataSource = courseDataSource;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<Course> allCourses = courseDataSource.findAll();
        model.addAttribute("courses", allCourses);

        return "courses";
    }
}
