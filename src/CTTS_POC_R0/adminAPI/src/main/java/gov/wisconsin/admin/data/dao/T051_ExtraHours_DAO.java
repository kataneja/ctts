package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T051_ExtraHours_Cargo;
import gov.wisconsin.admin.data.pojo.T051_ExtraHours_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T051_ExtraHours_DAO extends JpaRepository<T051_ExtraHours_Cargo, T051_ExtraHours_PK>, FwDAO {}
