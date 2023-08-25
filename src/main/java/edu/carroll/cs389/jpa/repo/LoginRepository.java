package edu.carroll.cs389.jpa.repo;

import java.util.List;

import edu.carroll.cs389.jpa.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Integer> {
    List<Login> findByUsernameIgnoreCase(String username);
}