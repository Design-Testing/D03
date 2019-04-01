
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.hacker.userAccount.id=?1")
	Collection<Application> findAllByHackerId(Integer hackerUAId);

	@Query("select a from Application a join a.position p where p.company.userAccount.id=?1")
	Collection<Application> findAllByCompanyId(Integer companyUAId);
}
