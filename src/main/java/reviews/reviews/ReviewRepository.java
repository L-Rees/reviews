package reviews.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByGenresContains(Genre genre);

	Collection<Review> findByGenresId(long id);

	Collection<Review> findByAuthorContains(Author author);

}
