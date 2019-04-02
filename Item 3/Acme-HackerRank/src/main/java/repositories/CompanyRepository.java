
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("select c from Company c where c.userAccount.id=?1")
	Company findByUserId(Integer companyId);

	@Query("select p.company from Problem p where p.id=?1")
	Company findCompanyByProblem(int problemId);

	@Query("select p.company from Position p where p.id=?1")
	Company findCompanyByPosition(int positionId);

}
