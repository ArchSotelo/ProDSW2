package com.cibertec.ERP_Product.config;


import com.cibertec.ERP_Product.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if(authHeader != null &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7).trim();

            if(token.isEmpty()) {
                filterChain.doFilter(
                        request,
                        response);
                return;
            }

            if(jwtService.isTokenValid(token)) {

                String correo =
                        jwtService.extractUsername(token);

               UsernamePasswordAuthenticationToken auth =
                       new UsernamePasswordAuthenticationToken(
                               correo,
                               null,
                               null);

               SecurityContextHolder
                      .getContext()
                      .setAuthentication(auth);
            }
        }

        filterChain.doFilter(
                request,
                response);
    }
}
