package reviews.reviews;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Author {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;

	@JsonIgnore
	@OneToMany
	private Collection<Review> reviews;
	
	public Collection<Review> getReviews(){
		return reviews;
	}
	
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	
	public Author() {
		
	}
	public Author(String name, Review...reviews) {
		this.name = name;
		this.reviews = new HashSet<>(Arrays.asList(reviews));
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
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
}
