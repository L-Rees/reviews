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
	private CommentRepository commentRepo;
	
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
	@Test
	public void shouldHaveTwoCommentsOnOneReview() {
		Genre sf = genreRepo.save(new Genre("science fiction"));
		Review book1 = reviewRepo.save(new Review("book1 title", "book1 review","url", sf));
		Author author = authorRepo.save(new Author("author name", book1));
		long reviewId = book1.getId();
		Comment testComment1 = new Comment("comment author", book1, "comment text");
		testComment1 = commentRepo.save(testComment1);
		long testComment1Id = testComment1.getId();
		Comment testComment2 = new Comment("comment author2", book1, "comment2 text");
		testComment2 = commentRepo.save(testComment2);
		long testComment2Id = testComment2.getId();
		entityManager.flush();
		entityManager.clear();
		Iterable<Comment> comments = commentRepo.findAll();
		assertThat(comments, containsInAnyOrder(testComment1, testComment2));
		Optional<Comment> testComment1Result = commentRepo.findById(testComment1Id);
		testComment1 = testComment1Result.get();
		Optional<Comment> testComment2Result = commentRepo.findById(testComment2Id);
		testComment2 = testComment2Result.get();
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		book1 = reviewResult.get();
		assertThat(testComment1.getCommentAuthor(), is("comment author"));
		assertThat(testComment2.getCommentAuthor(), is("comment author2"));
		assertThat(testComment1.getReview(), is(book1));
		assertThat(testComment2.getReview(), is(book1));
		assertThat(book1.getComments(), containsInAnyOrder(testComment1, testComment2));
		
	}
	
	
	
	
}
