package it.tino.springdemo.course.persistence;

import it.tino.springdemo.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
