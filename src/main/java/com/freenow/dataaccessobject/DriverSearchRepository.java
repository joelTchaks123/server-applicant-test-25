package com.freenow.dataaccessobject;

import com.freenow.domainobject.DriverDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverSearchRepository  extends CrudRepository<DriverDO, Long>, JpaRepository<DriverDO, Long>,
        JpaSpecificationExecutor<DriverDO> {

}
