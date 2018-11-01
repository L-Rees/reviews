package reviews.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {

	@Resource
	private CommentRepository commentRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@RequestMapping("/add-comment")
	public String addComment(String commentAuthor, Long reviewId, String commentText) {
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Comment newComment = new Comment(commentAuthor, review, commentText);
		commentRepo.save(newComment);
		return "redirect:/review?id=" + reviewId;
	}
	@RequestMapping("/del-comment")
	public String deleteComment(Long commentId, Long reviewId) {
		commentRepo.deleteById(commentId);
		return "redirect:/review?id=" + reviewId;
	}
	
}
