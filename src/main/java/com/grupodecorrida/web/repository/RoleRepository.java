package com.grupodecorrida.web.repository;

import com.grupodecorrida.web.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
