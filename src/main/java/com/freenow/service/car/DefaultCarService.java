package com.freenow.service.car;

import com.freenow.dataaccessobject.CarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.CarStatut;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some Car specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository CarRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.CarRepository = carRepository;
    }


    /**
     * Selects a Car by id.
     *
     * @param carId
     * @return found Car
     * @throws EntityNotFoundException if no Car with the given id was found.
     */
    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarChecked(carId);
    }


    /**
     * Creates a new Car.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a Car already exists with the given username, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;
        try
        {
            car = CarRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a Car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }


    /**
     * Deletes an existing Car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no Car with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarChecked(carId);
        carDO.setDeleted(true);
    }


    /**
     * Update the location for a Car.
     *
     * @param carId
     * @param rating
     * @param carStatut
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateInfoCar(long carId, BigDecimal rating, CarStatut carStatut) throws EntityNotFoundException
    {
        CarDO CarDO = findCarChecked(carId);
        CarDO.setRating(rating);
        CarDO.setCarStatut(carStatut);
    }


    /**
     * Find all Cars by online state.
     *
     * @param carStatut
     */
    @Override
    public List<CarDO> find(CarStatut carStatut)
    {
        return CarRepository.findByCarStatut(carStatut);
    }


    private CarDO findCarChecked(Long carId) throws EntityNotFoundException
    {
        return CarRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

}
