package com.tidiane.ColocSn.Controller;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tidiane.ColocSn.Entity.Rent;
import com.tidiane.ColocSn.Repository.RentRepository;
import com.tidiane.ColocSn.Service.RentService;

@RestController
@RequestMapping("api/v1/rent")
public class RentController {
    @Autowired RentRepository repository;
    @Autowired RentService service;

    @PostMapping
    public Rent createRent(@RequestBody Rent rent, @AuthenticationPrincipal Jwt jwt, LocalDateTime createdAt){
        rent.setOwnerId(jwt.getSubject());
        rent.setCreationDate(createdAt);
        return repository.save(rent);
    }

    @GetMapping("/{id}")
    public Optional<Rent> showRent(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    public List<Rent> showRents(){
        return repository.findAll();
    }

    @GetMapping("/mine")
    public List<Rent> myRents(@AuthenticationPrincipal Jwt jwt){
        return repository.findByOwnerId(service.userIdJwt(jwt));
    }

    @DeleteMapping("/{id}")
    public void delRent(@PathVariable Long id){
        repository.deleteById(id);
    }
}
