package reviews.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreRestController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private GenreRepository genreRepo;
	
	@RequestMapping("")
	public Iterable<Genre> findAllGenres(){
		return genreRepo.findAll();
	}
	@RequestMapping("/{id}")
	public Optional<Genre> findOneGenre(@PathVariable long id){
		return genreRepo.findById(id);
	}
	@RequestMapping("/{genreName}/reviews")
	public Collection<Review> findAllReviewsByGenre(@PathVariable(value="genreName") String genreName){
	Genre genre = genreRepo.findByNameIgnoreCaseLike(genreName);
	return reviewRepo.findByGenresContains(genre);
	}
	
}
