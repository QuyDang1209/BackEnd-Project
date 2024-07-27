package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.role r WHERE r.id = :roleId")
    List<User> findRoleById(@Param("roleId") Long roleId);
    User findByUsername(String username);
}
