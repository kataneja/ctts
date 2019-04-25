package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T024_Category_Cargo;
import gov.wisconsin.admin.data.pojo.T024_Category_PK;
import gov.wisconsin.framework.data.FwDAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface T024_Category_DAO extends JpaRepository<T024_Category_Cargo, T024_Category_PK>, FwDAO {
	@Query(value = "SELECT cat.project_nam, sub.subsystem_name FROM T024_Category cat, T001_Subsystem sub WHERE cat.project_nam = sub.subsystem_name", nativeQuery = true)
	public List<Object[]> getLinkedNames();
	
	@Query(value = "SELECT * FROM T024_Category WHERE project_nam = :projectName", nativeQuery = true)
	public List<T024_Category_Cargo> findCatgoryByProject(@Param("projectName") String projectName);

	@Query(value = "SELECT * FROM T024_Category WHERE project_nam = :projectName and category_nam = :categoryNam", nativeQuery = true)
	public List<T024_Category_Cargo> findCatgoryByProjectAndCategory(@Param("projectName") String projectName, @Param("categoryNam") String categoryNam);
}