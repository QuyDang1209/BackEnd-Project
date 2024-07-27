package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.Comment;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.res.CommentDTORes;
import com.cg.spb_houseforrent.service.CommentService;
import com.cg.spb_houseforrent.service.UserMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cg.spb_houseforrent.util.JwtUtil;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserMemberService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping
    public ResponseEntity<CommentDTORes> createComment(@RequestBody CommentDTORes commentDTO) {
        CommentDTORes createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    /*
    @PostMapping("/forrent/{houseId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long houseId, @RequestBody CommentDTORes commentDto,
                                              @RequestHeader("Authorization") String token) {

        try {
            // Validate token and extract user information
            String username = jwtUtil.extractUsername(token.substring(7));
            User user = userService.findByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            Comment comment = commentService.addComment(houseId, user, commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
     */

    @PostMapping("/forrent/{houseId}")
    public ResponseEntity<?> addComment(@PathVariable Long houseId, @RequestBody CommentDTORes commentDto,
                                        @RequestHeader("Authorization") String token) {
        if (houseId == null || houseId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing house ID");
        }

        if (commentDto == null || commentDto.getContent() == null || commentDto.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing comment content");
        }

        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing or invalid Authorization header");
        }

        try {
            // Validate token and extract user information
            String username = jwtUtil.extractUsername(token.substring(7));
            User user = userService.findByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }

            Comment comment = commentService.addComment(houseId, user, commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
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
