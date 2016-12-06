package com.plateauu.jba.repository;

import com.plateauu.jba.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
