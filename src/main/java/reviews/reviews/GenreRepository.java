package reviews.reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {

	Collection<Genre> findByReviewsContains(Review review);

}
