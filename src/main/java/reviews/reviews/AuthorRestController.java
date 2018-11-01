package reviews.reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private AuthorRepository authorRepo;
	
	@RequestMapping("")
	public Iterable <Author> findAllAuthors(){
		return authorRepo.findAll();
	}
	@RequestMapping("/{id}")
	public Optional<Author> findOneAuthor(@PathVariable long id){
		return authorRepo.findById(id);
	}
	@RequestMapping("/{id}/reviews")
	public Collection<Review> findAllReviewsByAuthor(@PathVariable(value="authorId") long authorId){
		Optional<Author> authorResult = authorRepo.findById(authorId);
		Author author = authorResult.get();
		return reviewRepo.findByAuthorContains(author);
	}
	
	
}
