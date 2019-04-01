
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Hacker;

public interface HackerRepository extends JpaRepository<Hacker, Integer> {

	@Query("select h from Hacker h where h.userAccount.id=?1")
	Hacker findByUserId(Integer hackerId);

}
