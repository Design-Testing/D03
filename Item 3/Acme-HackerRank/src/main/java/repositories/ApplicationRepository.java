
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

	@Query("select a from Application a join a.position p where p.company.userAccount.id=?1 AND a.status='ACCEPTED'")
	Collection<Application> findAllAcceptedByCompany(int companyUAId);

	@Query("select a from Application a join a.position p where p.company.userAccount.id=?1 AND a.status='REJECTED'")
	Collection<Application> findAllRejectedByCompany(int companyUAId);

	@Query("select a from Application a join a.position p where p.company.userAccount.id=?1 AND a.status='SUBMITTED'")
	Collection<Application> findAllSubmittedByCompany(int companyUAId);

	@Query("select a from Application a where a.status='ACCEPTED' AND a.hacker.userAccount.id=?1")
	Collection<Application> findAllAcceptedByHacker(int hackerUAId);

	@Query("select a from Application a where a.status='REJECTED' AND a.hacker.userAccount.id=?1")
	Collection<Application> findAllRejectedByHacker(int hackerUAId);

	@Query("select a from Application a where a.status='SUBMITTED' AND a.hacker.userAccount.id=?1")
	Collection<Application> findAllSubmittedByHacker(int hackerUAId);

	@Query("select a from Application a where a.status='PENDING' AND a.hacker.userAccount.id=?1")
	Collection<Application> findAllPendingByHacker(int hackerUAId);

	@Query("select a from Application a where a.problem.id=?1")
	Collection<Application> findAllByProblem(Integer problemId);
}
