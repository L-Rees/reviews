package reviews.reviews;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	Author findByReviewsContains(Review review);

}
