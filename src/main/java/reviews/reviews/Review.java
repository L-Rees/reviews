package reviews.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String title;

	@Lob
	private String reviewText;

	@JsonIgnore
	@ManyToMany
	private Collection<Genre> genres;

	@JsonIgnore
	@ManyToOne
	private Author author;

	@OneToMany(mappedBy = "review")
	private Collection<Comment> comments;

	private String imageUrl;

	public Author getAuthor() {
		return author;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getReviewText() {
		return reviewText;
	}

	public long getId() {
		return id;
	}

	public Collection<Comment> getComments() {
		return comments;
	}

	public Collection<Genre> getGenres() {
		return genres;
	}

	public Review() {

	}

	public Review(String title, String reviewText, String imageUrl, Genre... genres) {
		this.title = title;
		this.reviewText = reviewText;
		this.imageUrl = imageUrl;
		this.genres = new HashSet<>(Arrays.asList(genres));
	}

	public void addGenre(Genre newGenre) {
		genres.add(newGenre);
	}

	public void removeGenre(Genre genreToRemove) {
		genres.remove(genreToRemove);
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
