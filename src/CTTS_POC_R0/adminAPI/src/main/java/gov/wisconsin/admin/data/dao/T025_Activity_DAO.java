package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T025_Activity_Cargo;
import gov.wisconsin.admin.data.pojo.T025_Activity_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T025_Activity_DAO extends JpaRepository<T025_Activity_Cargo, T025_Activity_PK>, FwDAO {}
