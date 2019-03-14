package com.nhanhuynh.challenge;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //keep track of how many categories and int id
    private int idnum;

    @Size(min=2)
    private String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Car> cars;

    public Category() {
        this.cars = new HashSet<Car>();
    }

    public Category(int idnum) {

        switch (idnum) {
            case 1:
                this.type = "SubCompact and Compact";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 2:
                this.type = "Midsize";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
              break;
            case 3:
                this.type = "Full";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 4:
                this.type = "Crossover SUV";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 5:
                this.type = "Midsize SUV";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 6:
                this.type = "Fullsize SUV";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 7:
                this.type = "Midsize Pickup Truck";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 8:
                this.type = "Fullsize Pickup Truck";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 9:
                this.type = "Minivan";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 10:
                this.type = "Van";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 11:
                this.type = "Midsize Luxury";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 12:
                this.type = "Fullsize Luxury";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 13:
                this.type = "Midsize Luxury SUV";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 14:
                this.type = "Fullsize Luxury SUV";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
            case 15:
                this.type = "Racers, Sports, and Convertibles";
                this.cars = new HashSet<Car>();
                this.idnum = idnum;
                break;
        }
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public int getIdnum() {
        return idnum;
    }

    public void setIdnum(int idnum) {
        this.idnum = idnum;
    }
}
