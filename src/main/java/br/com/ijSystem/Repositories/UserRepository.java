package br.com.ijSystem.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ijSystem.Entities.UserEntities;
@Repository
public interface UserRepository extends JpaRepository<UserEntities, Long> {
	
	Optional<UserEntities> findByLogin(String login);
}