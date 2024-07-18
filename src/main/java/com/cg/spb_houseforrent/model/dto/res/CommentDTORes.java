package com.cg.spb_houseforrent.model.dto.res;

import com.cg.spb_houseforrent.model.Comment;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTORes {
    private Long id;
    private String content;
    private UserDTO users;
    private Long forrents;
    public CommentDTORes(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.users = new UserDTO(comment.getUsers());
        this.forrents = comment.getForrents().getId();
    }
}
