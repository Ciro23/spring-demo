package it.tino.springdemo.user.persistence;

import it.tino.springdemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
