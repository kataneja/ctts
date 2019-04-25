package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T003_CustomerArea_Cargo;
import gov.wisconsin.admin.data.pojo.T003_CustomerArea_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T003_CustomerArea_DAO extends JpaRepository<T003_CustomerArea_Cargo, T003_CustomerArea_PK>, FwDAO {}