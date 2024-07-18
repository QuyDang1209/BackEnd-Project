package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.res.CommentDTORes;
import com.cg.spb_houseforrent.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping
    public ResponseEntity<CommentDTORes> createComment(@RequestBody CommentDTORes commentDTO) {
        CommentDTORes createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTORes> getCommentById(@PathVariable Long id) {
        CommentDTORes comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTORes>> getAllComments() {
        List<CommentDTORes> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/forrent/{forrentId}")
    public ResponseEntity<List<CommentDTORes>> getAllCommentsByForrentId(@PathVariable Long forrentId) {
        List<CommentDTORes> comments = commentService.getAllCommentsByForrentId(forrentId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
