package reviews.reviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;

	private String commentAuthor;

	@ManyToOne
	private Review review;
	private String commentText;

	public String getCommentAuthor() {
		return commentAuthor;
	}

	public String getCommentText() {
		return commentText;
	}

	public Review getReview() {
		return review;
	}

	public long getId() {
		return id;
	}

	public Comment() {

	}

	public Comment(String commentAuthor, Review review, String commentText) {
		this.commentAuthor = commentAuthor;
		this.review = review;
		this.commentText = commentText;
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
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
