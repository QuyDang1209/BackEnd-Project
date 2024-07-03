package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
