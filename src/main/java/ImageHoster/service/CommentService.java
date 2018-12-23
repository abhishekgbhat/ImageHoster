package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void createComment(Comment comment) {
        commentRepository.createComment(comment);
    }

    public Comment getComment(Integer commentId) {
        return commentRepository.getComment(commentId);
    }

    public List<Comment> getComments(Integer id) {
        return commentRepository.getAllComments(id);
    }
}
