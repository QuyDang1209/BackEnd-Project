package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.TypeHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeHouseRepository extends JpaRepository<TypeHouse, Long> {
}
