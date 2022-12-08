package com.freenow.controller;

import com.freenow.controller.mapper.CarMapper;
import com.freenow.datatransferobject.CarDTO;
import com.freenow.domainobject.CarDO;
import com.freenow.domainvalue.CarStatut;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import com.freenow.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarCRUDController {
    private final CarService carService;


    @Autowired
    public CarCRUDController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }


//    @PutMapping("/{carId}")
//    public void updateInfoCar(@PathVariable long carId, @RequestParam String licensePlate, @RequestParam int seatCount,
//                              @RequestParam boolean
//                              @RequestParam BigDecimal rating, @RequestParam CarStatut carStatut)
    @PutMapping("/{carId}")
    public void updateInfoCar(@PathVariable long carId, @RequestParam BigDecimal rating, @RequestParam CarStatut carStatut)
            throws EntityNotFoundException, ConstraintsViolationException
    {
        carService.updateInfoCar(carId, rating, carStatut);
    }


    @GetMapping
    public List<CarDTO> findCars(@RequestParam CarStatut carStatut)
    {
        return CarMapper.makeCarDTOList(carService.find(carStatut));
    }
}
