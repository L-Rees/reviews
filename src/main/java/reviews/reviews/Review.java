package reviews.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;




@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;
	
	private String title;
	private String review;
	
	@ManyToMany
	private Collection<Genre> genres;
	
	@ManyToOne
	private Author author;
	
	public Author getAuthor() {
		return author;
	}
	
	
	public String getTitle(){
		return title;
	}
	public String getReview() {
		return review;
	}
	public long getId() {
		return id;
	}
	public Collection <Genre> getGenres(){
		return genres;
	}
	
	public Review() {
		
	}
	public Review(String title, String review, Genre...genres) {
		this.title = title;
		this.review = review;		
		this.genres = new HashSet<>(Arrays.asList(genres));
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
