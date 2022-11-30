package com.freenow.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(
        name = "manufacturer",
        uniqueConstraints = @UniqueConstraint(name = "uc_designation", columnNames = {"designation"})
)
public class ManufacturerDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Designation can not be null!")
    private String designation;

    @Column(nullable = false)
    private Boolean deleted = false;

    @OneToMany (mappedBy = "manufacturerDO")
    private List<CarDO> listCarsDO;

    public ManufacturerDO() {
    }

    public ManufacturerDO(String designation) {
        this.designation = designation;
        this.deleted = false;
    }

    public ManufacturerDO(String designation, List<CarDO> listCarsDO) {
        this.designation = designation;
        this.listCarsDO = listCarsDO;
        this.deleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<CarDO> getListCarsDO() {
        return listCarsDO;
    }

    public void setListCarsDO(List<CarDO> listCarsDO) {
        this.listCarsDO = listCarsDO;
    }
}
