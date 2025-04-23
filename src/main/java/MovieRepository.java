import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movi.booking.app.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
	// Custom query methods if needed
	List<Movie> findByTitleContainingIgnoreCase(String title);

	List<Movie> findTop4ByOrderByReleaseDateDesc();

}
