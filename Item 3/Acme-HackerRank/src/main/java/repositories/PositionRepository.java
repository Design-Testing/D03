
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Query("select p from Position p where p.ticker = ?1")
	Collection<Position> getPositionWithTicker(String ticker);

	@Query("select p from Position p where p.company.userAccount.id=?1")
	Collection<Position> findAllPositionByCompanyId(int id);

	@Query("select p from Position p where p.mode = 'FINAL'")
	Collection<Position> findAllFinalMode();

	@Query("select p from Position p where p.mode='FINAL' AND p.company.id=?1")
	Collection<Position> findAllFinalModeByCompany(int id);

	//TODO:Hay que corregirla
	@Query("select distinct p from Position p where p.mode='FINAL' AND (?1='' OR p.title LIKE CONCAT('%',?1,'%') OR p.description LIKE CONCAT('%',?1,'%') " + "OR p.profile LIKE CONCAT('%',?1,'%') OR p join p.skills s where s LIKE CONCAT('%',?1,'%') "
		+ "OR p join p.technologies t where t LIKE CONCAT('%',?1,'%') OR p join p.company c where c.commercialName LIKE CONCAT('%',?1,'%'))")
	Collection<Position> findPositions(String key);

}
