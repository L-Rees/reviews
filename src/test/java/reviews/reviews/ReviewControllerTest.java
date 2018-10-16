package reviews.reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

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
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long reviewId = 1L;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("review")));
		
	}
	
}
