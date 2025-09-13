package com.tidiane.ColocSn.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    Jwt jwt;

    @PostMapping
    public Rent createRent(@RequestBody Rent rent){
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
    public List<Rent> myRents(){
        return repository.findByOwner_UserId(service.userIdJwt(jwt));
    }

    @DeleteMapping
    public void delRent(@RequestBody Rent rent){
        repository.delete(rent);
    }
}
