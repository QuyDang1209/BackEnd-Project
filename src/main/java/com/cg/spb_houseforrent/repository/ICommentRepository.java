package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Comment;
import com.cg.spb_houseforrent.model.dto.res.CommentDTORes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.CommentDTORes(c) " +
            "FROM Comment c WHERE c.forrents.id = :forrentId")
    List<CommentDTORes> findByForrentsId(@Param("forrentId") Long forrentId);
}
