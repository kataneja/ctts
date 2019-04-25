package gov.wisconsin.admin.data.dao;

import gov.wisconsin.framework.data.FwDAO;

import gov.wisconsin.admin.data.pojo.T049_RefData_PK;
import gov.wisconsin.admin.data.pojo.T049_RefData_Cargo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface T049_RefData_DAO extends JpaRepository<T049_RefData_Cargo, T049_RefData_PK>, FwDAO {}
