package com.app.conation.jwt;

import com.app.conation.domain.User;
import com.app.conation.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtProvider {

    @Value("${properties.jwt.secretKey}")
    private String secretKey;

    private final long jwtValidPeriod = 1000 * 60L * 60L * 24 * 100; // 100일

    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtProvider(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getJWT (User user, List<String> roles) {
        Date now = new Date();

        return Jwts.builder()
            .setHeader(setHeader())
            .setIssuedAt(now)
            .setClaims(setPayloads(user, roles))
            .setExpiration(getExpiredTime())
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    private Map<String, Object> setHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return header;
    }

    private Claims setPayloads(User user, List<String> roles) {
        Claims payloads = Jwts.claims().setSubject(String.valueOf(user.getId()));

        payloads.put("userIdx", user.getId());
        payloads.put("email", user.getNickname());
        payloads.put("roles", roles);

        return payloads;
    }

    private Date getExpiredTime() {
        Date expireDate = new Date();
        expireDate.setTime(expireDate.getTime() + jwtValidPeriod);
        return expireDate;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public boolean validateToken (String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
