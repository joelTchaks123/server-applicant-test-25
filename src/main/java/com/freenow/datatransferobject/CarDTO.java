package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domainvalue.CarStatut;
import com.freenow.domainvalue.EngineType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    private Long id;
    @NotNull(message = "Username can not be null!")
    private String licensePlate;

    private Integer seatCount;

    private BigDecimal rating;

    private EngineType engineType;

    private CarStatut carStatut;



    public CarDTO() {
    }

    public CarDTO(Long id, String licensePlate, Integer seatCount, BigDecimal rating, EngineType engineType, CarStatut carStatut) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.rating = rating;
        this.engineType = engineType;
        this.carStatut = carStatut;
    }

    private CarDTO(Long id, String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
    }
    
    public static CarDTOBuilder newBuilder(){
        return new CarDTOBuilder();
    }
@JsonProperty
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public CarStatut getCarStatut() {
        return carStatut;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;

        private Integer seatCount;

        private BigDecimal rating;

        private EngineType engineType;

        private CarStatut carStatut;
        public CarDTO.CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTO.CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTO.CarDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTO.CarDTOBuilder setRating(BigDecimal rating) {
            this.rating = rating;
            return this;
        }

        public CarDTO.CarDTOBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTO.CarDTOBuilder setCarStatut(CarStatut carStatut) {
            this.carStatut = carStatut;
            return this;
        }

        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate, seatCount, rating, engineType, carStatut);
        }

    }
}
