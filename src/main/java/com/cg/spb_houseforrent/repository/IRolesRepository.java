package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Role, Long> {
}
