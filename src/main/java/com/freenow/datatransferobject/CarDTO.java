package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    private Long id;
    @NotNull(message = "Username can not be null!")
    private String licensePlate;

//    private Integer seatCount;
//
//    private Boolean convertible;
//
//    private BigDecimal rating;

//    private EngineType engineType;
//
//    private CarStatut carStatut;
//
//    private ManufacturerDO manufacturerDO;


    public CarDTO() {
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
    
    public static class CarDTOBuilder
    {
        private Long id;
        private String licensePlate;


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


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, licensePlate);
        }

    }
}
