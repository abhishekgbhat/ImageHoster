package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;



    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String showComment(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String commentText,
                              @PathVariable("imageTitle") String title, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setUser(user);
        comment.setCreatedDate(new Date());
        comment.setImage(imageService.getImage(imageId));
        commentService.createComment(comment);
        return "redirect:/images/" + imageId + '/' + title;
    }
}
