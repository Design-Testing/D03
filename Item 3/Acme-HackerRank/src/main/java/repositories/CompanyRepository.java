
package repositories;

import java.util.Collection;

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

	/** The average, minimum, maximum and standard deviation of the number of positions per company */
	@Query("select avg(1.0+ (select count(p) from Position p where p.company.id=c.id) -1.0), min(1.0+ (select count(p) from Position p where p.company.id=c.id) -1.0), max(1.0+ (select count(p) from Position p where p.company.id=c.id) -1.0), stddev(1.0+ (select count(p) from Position p where p.company.id=c.id) -1.0) from Company c")
	Double[] getStatisticsOfPositionsPerCompany();

	/** Companies that have offered more positions **/
	@Query("select g from Company g where (1.0 + (select count(e) from Position e where e.company.id=g.id) - 1.0)=(select max(1.0 + (select count(en) from Position en where en.company.id=b.id) - 1.0) from Company b)")
	Collection<Company> getCompaniesMorePositions();

}
