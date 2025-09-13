package com.tidiane.ColocSn.Repository;

import com.tidiane.ColocSn.Entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
  List<Rent> findByOwner_UserId(String ownerId);

}
