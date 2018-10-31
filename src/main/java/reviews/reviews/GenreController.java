package reviews.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GenreController {

	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private AuthorRepository authorRepo;
	
	@RequestMapping("/add-genre")
	public String addGenre(@RequestParam(value="reviewId")Long reviewId, String name) {
		Genre newGenre = genreRepo.findByNameIgnoreCaseLike(name);
		if (newGenre==null) {
			newGenre = new Genre(name);
			genreRepo.save(newGenre);
		}
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Genre existing = genreRepo.findByNameIgnoreCaseLike(name);
		if (!review.getGenres().contains(existing)) {
			review.addGenre(newGenre);
			reviewRepo.save(review);
		}
		return "redirect:/review?id=" + reviewId;
	}
	
	@RequestMapping("/remove-genre-button")
	public String removeGenreButton(@RequestParam Long genreId, @RequestParam Long reviewId) {
		Optional<Genre> genreToRemoveResult = genreRepo.findById(genreId);
		Genre genreToRemove = genreToRemoveResult.get();
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		
		
		review.removeGenre(genreToRemove);
		reviewRepo.save(review);
		
		return "redirect:/review?id=" + reviewId;
	}
	//add genres with AJAX
	@RequestMapping("/all-genres-ajax")
	public String showAllGenres(Model model) {
		model.addAttribute("genres", genreRepo.findAll());
		return "genresAjax";
	}
	
	
	
}
