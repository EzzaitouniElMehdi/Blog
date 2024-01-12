package mehdi.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mehdi.blog.model.ERole;
import mehdi.blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
