package com.freenow.service.driver;

import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.search.SearchRequest;
import org.springframework.data.domain.Page;

public interface DriverSearchService {
    Page<DriverDO> searchDriver(SearchRequest request);
}
