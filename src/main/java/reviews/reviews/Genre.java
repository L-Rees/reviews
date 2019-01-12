package reviews.reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	@ManyToMany(mappedBy = "genres")
	private Collection<Review> reviews;

	public Collection<Review> getReviews() {
		return reviews;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Genre(String name) {
		this.name = name;
	}

	public Genre() {

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
		Genre other = (Genre) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
