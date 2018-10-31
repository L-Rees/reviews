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
	
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private AuthorRepository authorRepo;
	
	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value="id")long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);
		if(review.isPresent()) {
			model.addAttribute("review", review.get());
			model.addAttribute("genres", genreRepo.findByReviewsContains(review.get()));
			model.addAttribute("author", authorRepo.findByReviewsContains(review.get()));
			return "review";
		}
		throw new ReviewNotFoundException();
	}
	
	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return "reviews";
	}
	
	@RequestMapping("/genre")
	public String findOneGenre(@RequestParam(value="id")long id, Model model) throws GenreNotFoundException {
		Optional<Genre>genre = genreRepo.findById(id);
		if (genre.isPresent()) {
			model.addAttribute("genre", genre.get());
			model.addAttribute("reviews", reviewRepo.findByGenresContains(genre.get()));
			return "genre";
		}
		throw new GenreNotFoundException();
	}
	@RequestMapping("/show-genres")
	public String findAllGenres(Model model) {
		model.addAttribute("genres", genreRepo.findAll());
		return "genres";
	}
	@RequestMapping("/author")
	public String findOneAuthor(@RequestParam(value="id")long id, Model model) throws AuthorNotFoundException  {
		Optional<Author>author = authorRepo.findById(id);
		if (author.isPresent()) {
			model.addAttribute("author", author.get());
			model.addAttribute("reviews", reviewRepo.findByAuthorContains(author.get()));
			return "author";
		}
		throw new AuthorNotFoundException();
	}
	@RequestMapping("/show-authors")
	public String findAllAuthors(Model model) {
		model.addAttribute("authors", authorRepo.findAll());
		return "authors";
	}
	
	
		
}
