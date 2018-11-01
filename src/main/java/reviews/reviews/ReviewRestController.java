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
	@RequestMapping("/genres/{genreId}")
	public Collection <Review> findAllReviewsByGenre(@PathVariable(value="genreId") long genreId){
		Optional<Genre> genreResult = genreRepo.findById(genreId);
		Genre genre = genreResult.get();
		return reviewRepo.findByGenresContains(genre);
	}
	@RequestMapping("/authors/{authorId}")
	public Collection <Review> findAllReviewsByAuthor(@PathVariable(value="authorId") long authorId){
		Optional<Author> authorResult = authorRepo.findById(authorId);
		Author author = authorResult.get();
		return reviewRepo.findByAuthorContains(author);
	}
	
	
	
	
	
	
}
