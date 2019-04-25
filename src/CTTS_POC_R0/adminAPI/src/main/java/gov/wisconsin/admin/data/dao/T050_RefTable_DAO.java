package gov.wisconsin.admin.data.dao;

import gov.wisconsin.admin.data.pojo.T050_RefTable_Cargo;
import gov.wisconsin.admin.data.pojo.T050_RefTable_PK;
import gov.wisconsin.framework.data.FwDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface T050_RefTable_DAO extends JpaRepository<T050_RefTable_Cargo, T050_RefTable_PK>, FwDAO {}
