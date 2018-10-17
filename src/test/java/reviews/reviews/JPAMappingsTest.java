package reviews.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private GenreRepository genreRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Resource
	private TestEntityManager entityManager;

	@Test
	public void shouldSaveAndLoadGenre() {
		Genre genre = new Genre("genre name");
		genre = genreRepo.save(genre);
		long genreId = genre.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Genre> result = genreRepo.findById(genreId);
		Genre underTest = result.get();
		assertThat(underTest.getName(), is("genre name"));
	}

	@Test
	public void shouldGenerateTopicId() {
		Genre genre = new Genre("genre name");
		genre = genreRepo.save(genre);
		long genreId = genre.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(genreId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = new Review("title", "review", "url");
		review = reviewRepo.save(review);
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		Review underTest = result.get();
		assertThat(underTest.getTitle(), is("title"));
	}

	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("title", "review","url"));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();
		assertThat(reviewId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReviewToGenreRelationship() {
		Genre sf = genreRepo.save(new Genre("science fiction"));
		Genre fan = genreRepo.save(new Genre("fantasy"));
		Review review = new Review("fancy book title", "review","url", sf, fan);
		review = reviewRepo.save(review);
		long reviewId = review.getId();
		entityManager.flush();
		entityManager.clear();
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getGenres(), containsInAnyOrder(sf, fan));
	}

	@Test
	public void shouldSaveAndLoadAuthor() {
		Author author = authorRepo.save(new Author("author name"));
		long authorId = author.getId();
		entityManager.flush();
		entityManager.clear();
		Optional<Author> result = authorRepo.findById(authorId);
		Author underTest = result.get();
		assertThat(underTest.getName(), is("author name"));
	}

	@Test
	public void shouldGenerateAuthorId() {
		Author author = authorRepo.save(new Author("author name"));
		long authorId = author.getId();
		entityManager.flush();
		entityManager.clear();
		assertThat(authorId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReviewToAuthorRelationship() {
		Review book1 = reviewRepo.save(new Review("book1 title", "book1 review", "url"));
		Review book2 = reviewRepo.save(new Review("book2 title", "book2 review", "url"));
		Author author = authorRepo.save(new Author("author name", book1, book2));
		long authorId = author.getId();
		entityManager.flush();
		entityManager.clear();
		Optional<Author> result = authorRepo.findById(authorId);
		author = result.get();
		assertThat(author.getReviews(), containsInAnyOrder(book1, book2));
	}

	@Test
	public void shouldFindReviewsForGenre() {
		Genre sf = genreRepo.save(new Genre("science fiction"));
		Review book1 = reviewRepo.save(new Review("book1 title", "book1 review","url", sf));
		Review book2 = reviewRepo.save(new Review("book2 title", "book2 review","url", sf));
		entityManager.flush();
		entityManager.clear();
		Collection<Review> reviewsForGenre = reviewRepo.findByGenresContains(sf);
		assertThat(reviewsForGenre, containsInAnyOrder(book1, book2));
	}

	@Test
	public void shouldFindReviewsByGenreId() {
		Genre sf = genreRepo.save(new Genre("science fiction"));
		long genreId = sf.getId();
		Review book1 = reviewRepo.save(new Review("book1 title", "book1 review","url", sf));
		Review book2 = reviewRepo.save(new Review("book2 title", "book2 review","url", sf));
		entityManager.flush();
		entityManager.clear();
		Collection<Review> reviewsForGenre = reviewRepo.findByGenresId(genreId);
		assertThat(reviewsForGenre, containsInAnyOrder(book1, book2));
	}

}
