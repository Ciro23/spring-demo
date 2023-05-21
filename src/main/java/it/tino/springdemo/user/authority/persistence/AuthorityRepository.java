package it.tino.springdemo.user.authority.persistence;

import it.tino.springdemo.user.authority.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
