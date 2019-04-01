
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	@Query("select w from Application a join a.answer w where a.hacker.userAccount.id=?1")
	Collection<Answer> findAllByHackerId(Integer hackerUAId);

	@Query("select w from Application a join a.answer w join a.position p where p.company.userAccount.id=?1")
	Collection<Answer> findAllByCompanyId(Integer companyUAId);

}
