package com.freenow.service.driver;


import com.freenow.dataaccessobject.DriverSearchRepository;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.search.SearchRequest;
import com.freenow.domainvalue.search.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultDriverSearchService implements DriverSearchService{

    @Autowired
    private DriverSearchRepository driverSearchRepository;
@Override
    public Page<DriverDO> searchDriver(SearchRequest request) {
        SearchSpecification<DriverDO> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return driverSearchRepository.findAll(specification, pageable);
    }

}
