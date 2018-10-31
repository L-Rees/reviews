package reviews.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private AuthorRepository authorRepo;
	
	@RequestMapping("")
	public Iterable<Review> findAllReviews(){
		return reviewRepo.findAll();
	}
	@RequestMapping("/{id}")
	public Optional <Review> findOneReview(@PathVariable long id){
		return reviewRepo.findById(id);
	}
	@RequestMapping("/genres/{genreName}")
	public Collection <Review> findAllReviewsByGenre(@PathVariable(value="genreName") String genreName){
		Genre genre = genreRepo.findByNameIgnoreCaseLike(genreName);
		return reviewRepo.findByGenresContains(genre);
	}
	@RequestMapping("/authors/{authorName}")
	public Collection <Review> findAllReviewsByAuthor(@PathVariable(value="authorName") String authorName){
		Author author = authorRepo.findByNameIgnoreCaseLike(authorName);
		return reviewRepo.findByAuthorContains(author);
	}
	
	
	
	
	
	
}
