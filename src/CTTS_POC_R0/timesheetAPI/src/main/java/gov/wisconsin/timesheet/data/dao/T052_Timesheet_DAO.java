package gov.wisconsin.timesheet.data.dao;

import gov.wisconsin.framework.data.FwDAO;
import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_Cargo;
import gov.wisconsin.timesheet.data.pojo.T052_Timesheet_PK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface T052_Timesheet_DAO extends JpaRepository<T052_Timesheet_Cargo, T052_Timesheet_PK>, FwDAO {
	@Query(value = "SELECT * FROM T052_Timesheet WHERE staff_id = :staffId", nativeQuery = true)
	public List<T052_Timesheet_Cargo> findByStaffId(@Param("staffId") String staffId);
}
