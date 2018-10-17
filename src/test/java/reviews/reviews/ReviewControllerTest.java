package reviews.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

	@Resource
	private MockMvc mvc;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;

	@Mock
	private Genre genre;

	@Mock
	private Genre anotherGenre;

	@Mock
	private Author author;

	@Mock
	private Author anotherAuthor;

	@MockBean
	private AuthorRepository authorRepo;

	@MockBean
	private ReviewRepository reviewRepo;

	@MockBean
	private GenreRepository genreRepo;

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long reviewId = 1L;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
	}

	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long reviewId = 1L;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(1L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(model().attribute("review", is(review)));
	}

	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}

	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", allReviews));
	}

	@Test
	public void shouldRouteToSingleGenreView() throws Exception {
		long genreId = 1L;
		when(genreRepo.findById(genreId)).thenReturn(Optional.of(genre));
		mvc.perform(get("/genre?id=1")).andExpect(view().name(is("genre")));
	}

	@Test
	public void shouldBeOkForSingleGenreView() throws Exception {
		long genreId = 1L;
		when(genreRepo.findById(genreId)).thenReturn(Optional.of(genre));
		mvc.perform(get("/genre?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotOkForSingleGenre() throws Exception {
		mvc.perform(get("/genre?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleGenreIntoModel() throws Exception {
		when(genreRepo.findById(3L)).thenReturn(Optional.of(genre));
		mvc.perform(get("/genre?id=3")).andExpect(model().attribute("genre", genre));
	}

	@Test
	public void shouldRouteToAllGenresView() throws Exception {
		mvc.perform(get("/show-genres")).andExpect(view().name(is("genres")));
	}

	@Test
	public void shouldBeOkForAllGenresView() throws Exception {
		mvc.perform(get("/show-genres")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllGenresIntoModel() throws Exception {
		Collection<Genre> allGenres = Arrays.asList(genre, anotherGenre);
		when(genreRepo.findAll()).thenReturn(allGenres);
		mvc.perform(get("/show-genres")).andExpect(model().attribute("genres", is(allGenres)));

	}

	@Test
	public void shouldRouteToSingleAuthorView() throws Exception {
		long authorId = 1L;
		when(authorRepo.findById(authorId)).thenReturn(Optional.of(author));
		mvc.perform(get("/author?id=1")).andExpect(view().name(is("author")));
	}

	@Test
	public void shouldBeOkForSingleAuthor() throws Exception {
		long authorId = 1L;
		when(authorRepo.findById(authorId)).thenReturn(Optional.of(author));
		mvc.perform(get("/author?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldBeNotOkForSingleAuthor() throws Exception {
		mvc.perform(get("/author?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleAuthorIntoModel() throws Exception {
		when(authorRepo.findById(3L)).thenReturn(Optional.of(author));
		mvc.perform(get("/author?id=3")).andExpect(model().attribute("author", author));
	}

	@Test
	public void shouldRouteToAllAuthorsView() throws Exception {
		mvc.perform(get("/show-authors")).andExpect(view().name(is("authors")));
	}

	@Test
	public void shouldBeOKForAllAuthorsView() throws Exception {
		mvc.perform(get("/show-authors")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllAuthorsIntoView() throws Exception {
		Collection<Author> allAuthors = Arrays.asList(author, anotherAuthor);
		when(authorRepo.findAll()).thenReturn(allAuthors);
		mvc.perform(get("/show-authors")).andExpect(model().attribute("authors", is(allAuthors)));
	}

}
