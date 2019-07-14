
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

	@Query("select a.problem from Application a where a.id=?1")
	Problem findProblemByApplication(int applicationId);

	@Query("select p from Problem p where p.position.id=?1")
	Collection<Problem> findProblemsByPosition(int positionId);

	@Query("select p from Problem p where p.company.userAccount.id=?1")
	Collection<Problem> findProblemsByCompany(int companyUAId);

	@Query("select a.problem from Application a where a.position.id=?1 AND a.hacker.userAccount.id=?2")
	Collection<Problem> findProblemsByPositionAndHacker(int positionId, int hackerUAId);

	@Query("select p from Problem p where p.position.id=?1 AND p.mode='FINAL'")
	Collection<Problem> findFinalProblemsByPosition(int positionId);

	@Query("select p from Problem p where p.company.userAccount.id=?1 AND p.mode='FINAL' AND p.position IS NULL ")
	Collection<Problem> findFinalNotAsignedProblemsByCompany(int id);

	//@Query("select pro from Problem pro join pro.position pos where exists (select a from Application a join a.position pos2 where a.hacker.id=?1 and pos2.id=pos.id)")
	//Collection<Problem> findAllByHacker(int hackerId);

	@Query("select p from Application a join a.problem p where a.hacker.id=?1")
	Collection<Problem> findAllByHacker(int hackerId);

}
