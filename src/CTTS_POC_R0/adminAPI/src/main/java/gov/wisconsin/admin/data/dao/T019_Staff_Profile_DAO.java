package gov.wisconsin.admin.data.dao;

import java.util.List;

import gov.wisconsin.framework.data.FwDAO;
import gov.wisconsin.admin.data.pojo.T019_Staff_Profile_PK;
import gov.wisconsin.admin.data.pojo.T019_Staff_Profile_Cargo;
import gov.wisconsin.admin.data.pojo.T019_T023_Staff_Security_Profile_Cargo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface T019_Staff_Profile_DAO extends JpaRepository<T019_Staff_Profile_Cargo, T019_Staff_Profile_PK>, FwDAO {
	
	@Query(value = "SELECT A.PRFL_ID,B.WINDOW_ID,B.ACCESS_CD FROM T019_STAFF_PROFILE A INNER JOIN T023_SECURITY_PROFILE_WIN B ON A.PRFL_ID=B.SECURITY_PROFILE_CD WHERE A.WI_UNIQ_USER_ID= :wid", nativeQuery = true)
	public List<T019_T023_Staff_Security_Profile_Cargo> findByWid(@Param("wid") String wid);
}