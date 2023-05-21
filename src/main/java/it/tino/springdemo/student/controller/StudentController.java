package it.tino.springdemo.student.controller;


import it.tino.springdemo.course.Course;
import it.tino.springdemo.course.persistence.CourseRepository;
import it.tino.springdemo.student.Student;
import it.tino.springdemo.student.persistence.StudentDataSource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("students")
public class StudentController {

    StudentDataSource studentDataSource;
    private final CourseRepository courseRepository;

    public StudentController(
        StudentDataSource studentDataSource,
        CourseRepository courseRepository
    ) {
        this.studentDataSource = studentDataSource;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<Student> allStudents = studentDataSource.findAll();
        model.addAttribute("students", allStudents);

        return "students/students";
    }

    @GetMapping("add")
    public String showStudentCreationForm(Model model) {
        List<Course> allCourses = courseRepository.findAll();

        model.addAttribute("student", new Student());
        model.addAttribute("allCourses", allCourses);

        return "students/add";
    }

    @PostMapping("add")
    public String save(@Valid Student student, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // I don't know how to preserve previously fetched courses
            List<Course> allCourses = courseRepository.findAll();
            model.addAttribute("allCourses", allCourses);

            return "students/add";
        }

        studentDataSource.save(student);

        return "redirect:/students";
    }
}
