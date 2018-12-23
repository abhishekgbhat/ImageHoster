package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment comment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return comment;
    }


    public Comment getComment(Integer commentId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> typedQuery =
                em.createQuery("SELECT c from Comment c where c.id =:commentId", Comment.class).setParameter(
                        "commentId",
                        commentId);
        Comment comment = typedQuery.getSingleResult();
        return comment;
    }


    public void deleteComment(Integer commentId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Comment comment = em.find(Comment.class, commentId);
            em.remove(comment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Comment> getAllComments(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> typedQuery =
                em.createQuery("SELECT c from Comment c where c.image.id =:imageId", Comment.class).setParameter(
                        "imageId",
                        imageId);
        List<Comment> comments = typedQuery.getResultList();
        return comments;
    }
}
