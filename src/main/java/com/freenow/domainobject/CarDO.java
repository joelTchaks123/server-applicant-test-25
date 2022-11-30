package com.freenow.domainobject;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.freenow.domainvalue.CarStatut;
import com.freenow.domainvalue.EngineType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
        name = "car",
        uniqueConstraints = @UniqueConstraint(name = "uc_licensePlate", columnNames = {"licensePlate"})
)
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "car_ID")
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "LicensePlate can not be null!")
    @Size(min = 5, max = 25)
    private String licensePlate;

    @Column(nullable = false)
    @NotNull(message = "SeatCount can not be null!")
    @Min(2)
    private Integer seatCount = 2;

    @Column(nullable = false)
    private Boolean convertible = false;

    @Column(nullable = true, scale = 1)
    @Min(1) @Max(5)
    private BigDecimal rating = null;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatut carStatut;

    @ManyToOne
    private ManufacturerDO manufacturerDO;

    public CarDO() {
    }

    public CarDO(String licensePlate) {
        this.licensePlate = licensePlate;
        this.seatCount = 2;
        this.convertible = false;
        this.rating = null;
        this.engineType = EngineType.ELECTRIC;
        this.carStatut = CarStatut.FREE;
        this.deleted = false;
        this.manufacturerDO = null;
    }

    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, BigDecimal rating, EngineType engineType, CarStatut carStatut) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType.ELECTRIC;
        this.carStatut = carStatut.FREE;
        this.deleted = false;
    }

    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, BigDecimal rating, EngineType engineType,
                 ManufacturerDO manufacturerDO) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.manufacturerDO = manufacturerDO;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public CarStatut getCarStatut() {
        return carStatut;
    }

    public void setCarStatut(CarStatut carStatut) {
        this.carStatut = carStatut;
    }

    public ManufacturerDO getManufacturerDO() {
        return manufacturerDO;
    }

    public void setManufacturerDO(ManufacturerDO manufacturerDO) {
        this.manufacturerDO = manufacturerDO;
    }
}