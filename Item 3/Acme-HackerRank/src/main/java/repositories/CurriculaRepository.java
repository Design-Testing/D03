
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;

@Repository
public interface CurriculaRepository extends JpaRepository<Curricula, Integer> {

	/** The average, minimum, maximum and standard deviation of the number of positions per company */
	@Query("select avg(1.0+ (select count(p) from Curricula p where p.hacker.id=c.id) -1.0), min(1.0+ (select count(p) from Curricula p where p.hacker.id=c.id) -1.0), max(1.0+ (select count(p) from Curricula p where p.hacker.id=c.id) -1.0), stddev(1.0+ (select count(p) from Curricula p where p.hacker.id=c.id) -1.0) from Hacker c")
	Double[] getStatisticsOfCurriculaPerHacker();
}
