package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T035_HolidayTime_Cargo;
import gov.wisconsin.admin.data.pojo.T035_HolidayTime_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T035_HolidayTime_DAO extends JpaRepository<T035_HolidayTime_Cargo, T035_HolidayTime_PK>, FwDAO {}
