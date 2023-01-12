package com.freenow.service.driver;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.CarStatut;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import java.util.List;

import com.freenow.service.car.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;
    private final CarService carService;

    public DefaultDriverService(final DriverRepository driverRepository, CarService carService)
    {
        this.driverRepository = driverRepository;
        this.carService = carService;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }

    /**
     * Update the free car for a driver.
     *
     * @param driverId
     * @param carId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void selectCarByDriver(long driverId, long carId) throws EntityNotFoundException, ConstraintsViolationException, CarAlreadyInUseException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        if  (driverDO.getDeleted() || driverDO.getOnlineStatus() == OnlineStatus.OFFLINE) {
            throw new ConstraintsViolationException("Driver must be ONLINE to select a car");
        }
        if (driverDO.getCarDO() != null)
            driverDO.getCarDO().setCarStatut(CarStatut.FREE);
        CarDO carDO = carService.find(carId);
        if (carDO.getDeleted() || carDO.getCarStatut() == CarStatut.BUSY) {
           throw new CarAlreadyInUseException("The car is busy or deleted and its id is : " + carId);
        }
        driverDO.setCarDO(carDO);
        carDO.setCarStatut(CarStatut.BUSY);
    }


    /**
     * Update the free car for a driver.
     *
     * @param driverId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void unSelectCarByDriver(long driverId) throws EntityNotFoundException, ConstraintsViolationException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        if  (driverDO.getDeleted() || driverDO.getOnlineStatus() == OnlineStatus.OFFLINE)
            throw new ConstraintsViolationException("Driver must be ONLINE to unselect a car");

        if  (driverDO.getCarDO() == null)
            throw new ConstraintsViolationException("This Driver doesn't have the car to unselect a car");

        driverDO .getCarDO().setCarStatut(CarStatut.FREE);
        driverDO.setCarDO(null);
    }

    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
