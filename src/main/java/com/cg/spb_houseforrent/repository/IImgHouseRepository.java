package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.ImgHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImgHouseRepository extends JpaRepository<ImgHouse, Long> {
    List<ImgHouse> findAllById(Long id);
//    void deleteAllByForrents(Forrent forrent);
}
