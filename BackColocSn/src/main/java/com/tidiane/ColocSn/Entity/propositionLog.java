package com.tidiane.ColocSn.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class propositionLog {
    @Id @GeneratedValue private Long propositionId;
    private String propositionName;
    private double propositionPrice;
    //Next time use google maps API
    private String propositionPlace;
    //Next time use Date format
    private String rentDuration;

}
