package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Comment;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.model.dto.res.CommentDTORes;
import com.cg.spb_houseforrent.repository.ICommentRepository;
import com.cg.spb_houseforrent.repository.IForrentRepository;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IForrentRepository forrentRepository;



    @Override
    public CommentDTORes createComment(CommentDTORes commentDTO) {
        Optional<User> userOptional = usersRepository.findById(commentDTO.getUsers().getId());
        Optional<Forrent> forrentOptional = forrentRepository.findById(commentDTO.getForrents());

        if (userOptional.isEmpty() || forrentOptional.isEmpty()) {
            throw new IllegalArgumentException("User or Forrent not found with provided IDs");
        }

        Forrent forrent = forrentOptional.get();

        // Create and save the comment
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setUsers(userOptional.get());
        comment.setForrents(forrent);

        Comment savedComment = commentRepository.save(comment);

        // Convert to DTO response
        return convertToDTORes(savedComment);
    }

    @Override
    public CommentDTORes getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("Comment not found with provided ID");
        }

        return convertToDTORes(commentOptional.get());
    }

    private CommentDTORes convertToDTORes(Comment comment) {
        CommentDTORes dtoRes = new CommentDTORes();
        dtoRes.setId(comment.getId());
        dtoRes.setContent(comment.getContent());
        dtoRes.setUsers(new UserDTO(comment.getUsers()));
        dtoRes.setForrents(comment.getForrents().getId());
        return dtoRes;
    }

    @Override
    public List<CommentDTORes> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(this::convertToDTORes)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);

        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("Comment not found with provided ID");
        }

        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDTORes> getAllCommentsByForrentId(Long forrentId) {
        List<CommentDTORes> comments = commentRepository.findByForrentsId(forrentId);
        return comments;
    }

    public Comment addComment(Long houseId, User user, CommentDTORes commentDto) {
        Forrent forrent = forrentRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("House not found"));
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setUsers(user);
        comment.setForrents(forrent);
        return commentRepository.save(comment);
    }
}
