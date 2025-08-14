package com.dabom.member.security.filter;

import com.dabom.member.security.dto.MemberDetailsDto;
import com.dabom.member.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.dabom.member.contants.JWTConstants.*;

public class JwtAuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String jwt = findDabomJWT(cookies);

        haveTokenLogic(jwt);
        filterChain.doFilter(request, response);
    }

    private String findDabomJWT(Cookie[] cookies) {
        String jwt = null;
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(Objects.equals(cookie.getName(), ACCESS_TOKEN)) {
                    jwt = cookie.getValue();
                }
                break;
            }
        }
        return jwt;
    }

    private void haveTokenLogic(String jwt) {
        if(jwt != null) {
            Claims claims = JwtUtils.getClaims(jwt);
            haveDabomTokenLogic(claims);
        }
    }

    private void haveDabomTokenLogic(Claims claims) {
        if(claims != null) {
            MemberDetailsDto dto = createDetailsFromToken(claims);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    dto,
                    null,
                    List.of(new SimpleGrantedAuthority(dto.getMemberRole().name()))
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private MemberDetailsDto createDetailsFromToken(Claims claims) {
        Integer idx = Integer.parseInt(JwtUtils.getValue(claims, TOKEN_IDX));
        String name = JwtUtils.getValue(claims, TOKEN_NAME);
        String role = JwtUtils.getValue(claims, TOKEN_USER_ROLE);
        return MemberDetailsDto.createFromToken(idx, name, role);
    }
}
