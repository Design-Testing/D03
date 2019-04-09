
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Hacker;

public interface HackerRepository extends JpaRepository<Hacker, Integer> {

	@Query("select h from Hacker h where h.userAccount.id=?1")
	Hacker findByUserId(Integer hackerId);

	/** The average, minimum, maximum and standard deviation of the number of applications per hacker */
	@Query("select avg(1.0+ (select count(p) from Application p where p.hacker.id=c.id) -1.0), min(1.0+ (select count(p) from Application p where p.hacker.id=c.id) -1.0), max(1.0+ (select count(p) from Application p where p.hacker.id=c.id) -1.0), stddev(1.0+ (select count(p) from Application p where p.hacker.id=c.id) -1.0) from Hacker c")
	Double[] getStatisticsOfApplicationsPerHacker();

	/** Hackers who have made more applications **/
	@Query("select g from Hacker g where (1.0 + (select count(e) from Application e where e.hacker.id=g.id) - 1.0)=(select max(1.0 + (select count(en) from Application en where en.hacker.id=b.id) - 1.0) from Hacker b)")
	Hacker[] getHackersMoreApplications();

}
