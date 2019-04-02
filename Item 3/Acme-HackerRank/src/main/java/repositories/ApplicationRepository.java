
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.hacker.userAccount.id=?1")
	Collection<Application> findAllByHackerId(Integer hackerUAId);

	@Query("select a from Application a join a.position p where p.company.userAccount.id=?1")
	Collection<Application> findAllByCompanyId(Integer companyUAId);

	@Query("select a from Application a where a.status='ACCEPTED' AND a.company.id=?1")
	Collection<Application> findAllAcceptedByCompany(int companyId);

	@Query("select a from Application a where a.status='REJECTED' AND a.company.id=?1")
	Collection<Application> findAllRejectedByCompany(int companyId);

	@Query("select a from Application a where a.status='SUBMITTED' AND a.company.id=?1")
	Collection<Application> findAllSubmittedByCompany(int companyId);

	@Query("select a from Application a where a.status='ACCEPTED' AND a.hacker.id=?1")
	Collection<Application> findAllAcceptedByHacker(int hackerId);

	@Query("select a from Application a where a.status='REJECTED' AND a.hacker.id=?1")
	Collection<Application> findAllRejectedByHacker(int hackerId);

	@Query("select a from Application a where a.status='SUBMITTED' AND a.hacker.id=?1")
	Collection<Application> findAllSubmittedByHacker(int hackerId);

	@Query("select a from Application a where a.status='PENDING' AND a.hacker.id=?1")
	Collection<Application> findAllPendingByHacker(int hackerId);
}
