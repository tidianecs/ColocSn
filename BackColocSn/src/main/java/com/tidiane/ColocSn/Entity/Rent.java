package com.tidiane.ColocSn.Entity;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rent {
    @Id @GeneratedValue private Long rentId;
    private String title;
    private double pricePerMonth;
    private RentStatus status;
    private String description;
    @ManyToOne private User owner;
    private LocalDateTime creationDate;

    Rent(){} //Empty constructor for jpa

    Rent(String title, double pricePerMonth, RentStatus status, String description, User owner, LocalDateTime creationDate){
        this.title = title;
        this.pricePerMonth = pricePerMonth;
        this.status = status.AVAILABLE;
        this.description = description;
        this.owner = owner;
        this.creationDate = creationDate.now();
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

    public void setOwner(User owner) {
        this.owner= owner;
    }
    public User getOwnerId() {
        return owner;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
}
