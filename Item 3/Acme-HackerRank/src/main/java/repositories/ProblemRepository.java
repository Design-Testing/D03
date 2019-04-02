
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

	@Query("select p.problems from Position p where p.id=?1")
	Collection<Problem> findProblemByPosition(int positionId);

}
