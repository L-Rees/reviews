package reviews.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@RequestMapping("review")
	public String findOneReview(@RequestParam(value="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		if(review.isPresent()) {
			model.addAttribute("reviews", review.get());
			return "review";
		}
		throw new ReviewNotFoundException();
	}
	
	
	
	
	
}
