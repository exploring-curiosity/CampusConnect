// package com.campusconnect.campusconnect.config;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.campusconnect.campusconnect.enums.Role;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.AllArgsConstructor;

// @AllArgsConstructor
// @Component
// public class JwtAuthFilter extends OncePerRequestFilter{
//     private final JwtUtil jwtUtil;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization");

//         if(authHeader != null && authHeader.startsWith("Bearer ")) {
//             String token = authHeader.substring(7);

//             try {
//                 String username = jwtUtil.extractUsername(token);
//                 Role role = jwtUtil.extractRole(token);

//                 SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name());
//                 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, List.of(authority));
                
//                 SecurityContextHolder.getContext().setAuthentication(authentication);
                
//             } catch (Exception e) {
//                 SecurityContextHolder.getContext();
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
