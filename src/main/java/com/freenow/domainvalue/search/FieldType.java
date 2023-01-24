package com.freenow.domainvalue.search;

import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public enum FieldType {

    BOOLEAN {
        public Object parse(String value) {
            return Boolean.valueOf(value);
        }
    },

    CHAR {
        public Object parse(String value) {
            return value.charAt(0);
        }
    },

    DATE {
        public Object parse(String value) {
            Object date = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                date = LocalDateTime.parse(value, formatter);
            } catch (Exception e) {
//                log.info("Failed parse field type DATE {}", e.getMessage());
            }

            return date;
        }
    },

    DOUBLE {
        public Object parse(String value) {
            return Double.valueOf(value);
        }
    },

    INTEGER {
        public Object parse(String value) {
            return Integer.valueOf(value);
        }
    },

    LONG {
        public Object parse(String value) {
            return Long.valueOf(value);
        }
    },

    STRING {
        public Object parse(String value) {
            return value;
        }
    },
    ONLINESTATUS {
        public Object parse(String value) {
            return com.freenow.domainvalue.OnlineStatus.valueOf(value);
        }
    },
    GEOCOORDINATE {
        public Object parse(String value) {
            String [] latLong = value.split(",");
            com.freenow.domainvalue.GeoCoordinate coordinate = new GeoCoordinate(Double.parseDouble(latLong[0]), Double.parseDouble(latLong[1]) );
            return coordinate;
        }
    },
    CARSTATUS {
        public Object parse(String value) {
            return com.freenow.domainvalue.CarStatut.valueOf(value);
        }
    },
    ENGINETYPE {
        public Object parse(String value) {
            return com.freenow.domainvalue.EngineType.valueOf(value);
        }
    },
    BIGDECIMAL {
        public Object parse(String value) {
            return BigDecimal.valueOf(Double.valueOf(value));
        }
    };

    public abstract Object parse(String value);

}
