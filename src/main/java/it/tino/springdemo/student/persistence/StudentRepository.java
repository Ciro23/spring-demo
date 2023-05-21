package it.tino.springdemo.student.persistence;

import it.tino.springdemo.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
