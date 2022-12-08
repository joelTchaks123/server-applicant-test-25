package com.freenow.service.car;

import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.CarStatut;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface CarService
{

    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateInfoCar(long carId, BigDecimal rating, CarStatut carStatut) throws EntityNotFoundException, ConstraintsViolationException;

    List<CarDO> find(CarStatut carStatut);

}
