package it.tino.springdemo;

import it.tino.springdemo.course.Course;
import it.tino.springdemo.student.Student;
import it.tino.springdemo.student.persistence.StudentRepository;
import it.tino.springdemo.user.User;
import it.tino.springdemo.user.authority.Authority;
import it.tino.springdemo.user.authority.persistence.AuthorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            AuthorityRepository authorityRepository
    ) {
        return runner -> {
            List<Course> courses = getCourses();
            List<Student> students = getStudents(courses);

            List<User> users = getUsers();
            List<Authority> authorities = getAuthorities(users);

            // Courses are saved by cascade when inserting users
            studentRepository.saveAll(students);

            // Users are saved by cascade when inserting authorities
            authorityRepository.saveAll(authorities);
        };
    }

    private List<Student> getStudents(List<Course> courses) {
        Student student1 = new Student("Mario", "Rossi", new HashSet<>(courses));
        Student student2 = new Student("Luigi", "Verdi", new HashSet<>(courses));
        Student student3 = new Student("Marco", "Bianchi", new HashSet<>(courses));
        Student student4 = new Student("Federico", "Neri", new HashSet<>(courses));

        return Arrays.asList(student1, student2, student3, student4);
    }

    private List<Course> getCourses() {
        Course course1 = new Course("Math");
        Course course2 = new Course("Science");
        Course course3 = new Course("Literature");

        return Arrays.asList(course1, course2, course3);
    }

    /**
     * The password for every user is always "password"
     */
    private List<User> getUsers() {
        User user = new User(
                "user",
                "{bcrypt}$2a$12$9IjPLyVeNPx9h0luHJMEjePRhmtMDTHiSlkh6iWIUETebLIWYPAh2",
                true
        );
        User admin = new User(
                "admin",
                "{bcrypt}$2a$12$9IjPLyVeNPx9h0luHJMEjePRhmtMDTHiSlkh6iWIUETebLIWYPAh2",
                true
        );
        User disabledUser = new User(
                "disabled",
                "{bcrypt}$2a$12$9IjPLyVeNPx9h0luHJMEjePRhmtMDTHiSlkh6iWIUETebLIWYPAh2",
                false
        );

        return Arrays.asList(user, admin, disabledUser);
    }

    private List<Authority> getAuthorities(List<User> users) {
        Authority user = new Authority(users.get(0), "ROLE_USER");
        Authority disabledUser = new Authority(users.get(2), "ROLE_USER");
        Authority admin1 = new Authority(users.get(1), "ROLE_USER");
        Authority admin2 = new Authority(users.get(1), "ROLE_ADMIN");

        return Arrays.asList(user, disabledUser, admin1, admin2);
    }
}
