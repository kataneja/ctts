package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T001_Subsystem_Cargo;
import gov.wisconsin.admin.data.pojo.T001_Subsystem_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T001_Subsystem_DAO extends JpaRepository<T001_Subsystem_Cargo, T001_Subsystem_PK>, FwDAO {}
