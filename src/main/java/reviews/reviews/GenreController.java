package reviews.reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	//use ajax to add genres to the database
	@RequestMapping(path="/genres/{genreName}", method = RequestMethod.POST)
	public String AddGenre(@PathVariable String genreName, Model model) {
		Genre genreToAdd = genreRepo.findByNameIgnoreCaseLike(genreName);
		if(genreToAdd == null) {
			genreToAdd = new Genre(genreName);
			genreRepo.save(genreToAdd);
		}
		model.addAttribute("genres", genreRepo.findAll());
		return "partials/genres-list";
	}
	
	//Use ajax to remove genres from the database
	@RequestMapping(path="/genres/remove/{genreName}", method=RequestMethod.POST)
	public String RemoveGenre(@PathVariable String genreName, Model model) {
		Genre genreToDelete = genreRepo.findByNameIgnoreCaseLike(genreName);
		if (genreRepo.findByNameIgnoreCaseLike(genreName) != null) {
			for(Review review: genreToDelete.getReviews()) {
				review.removeGenre(genreToDelete);
				reviewRepo.save(review);
			}
		}
		genreRepo.delete(genreToDelete);
		model.addAttribute("genres", genreRepo.findAll());
		return "partials/genres-list";
	}
}
