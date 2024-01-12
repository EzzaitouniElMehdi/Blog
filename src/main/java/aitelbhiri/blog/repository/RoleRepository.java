package aitelbhiri.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aitelbhiri.blog.model.ERole;
import aitelbhiri.blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
