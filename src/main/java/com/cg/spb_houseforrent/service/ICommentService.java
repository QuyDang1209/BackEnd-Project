package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.dto.res.CommentDTORes;

import java.util.List;

public interface ICommentService {
    CommentDTORes createComment(CommentDTORes commentDTO);
    CommentDTORes getCommentById(Long id);
    List<CommentDTORes> getAllComments();
    void deleteComment(Long id);

    List<CommentDTORes> getAllCommentsByForrentId(Long forrentId);
}
