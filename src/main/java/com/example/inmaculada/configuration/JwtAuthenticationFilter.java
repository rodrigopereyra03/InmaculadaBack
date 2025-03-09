package com.example.inmaculada.configuration;

import com.example.inmaculada.services.jwt.IUserService;
import com.example.inmaculada.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final IUserService userService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, IUserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final  String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String email;

        System.out.println("🔹 Entrando en JwtAuthenticationFilter");

        if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,"Bearer ")){
            System.out.println("❌ No se encontró el header Authorization o no tiene formato Bearer");
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        System.out.println("🔹 Token extraído: " + jwt);
        email = jwtUtil.extractUserName(jwt);
        System.out.println("🔹 Usuario extraído del token: " + email);

        if(StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("🔹 Cargando usuario desde UserDetailsService");

            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
            if (jwtUtil.isTokenValid(jwt,userDetails)){
                System.out.println("✅ Token válido. Autenticando usuario...");
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }else {
                System.out.println("❌ Token inválido");
            }
        }
        filterChain.doFilter(request,response);

    }
}
