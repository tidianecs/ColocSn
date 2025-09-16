package com.tidiane.ColocSn.Entity;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Rent {
    @Id @GeneratedValue private Long rentId;
    private String title;
    private double pricePerMonth;
    private RentStatus status;
    private String description;
    private String ownerId;
    private LocalDateTime creationDate;

    Rent(){} //Empty constructor for jpa

    Rent(String title, double pricePerMonth, RentStatus status, String description){
        this.title = title;
        this.pricePerMonth = pricePerMonth;
        this.status = status.AVAILABLE;
        this.description = description;
    }

    public Long getRentId() {
        return rentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }
    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }
    public RentStatus getStatus() {
        return status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId= ownerId;
    }
    public String getOwnerId() {
        return ownerId;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
