package com.tidiane.ColocSn.Service;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    public String userIdJwt(Jwt jwt){
        return jwt.getSubject();
    }
}
