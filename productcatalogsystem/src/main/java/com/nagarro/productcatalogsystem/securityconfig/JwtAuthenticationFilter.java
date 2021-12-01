package com.nagarro.productcatalogsystem.securityconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagarro.productcatalogsystem.helper.JwtUtil;
import com.nagarro.productcatalogsystem.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader=request.getHeader("Authorization");
        String userName=null;
        String jwtToken=null;
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken=requestTokenHeader.substring(7);
            try {
                userName=this.jwtUtil.extractUsername(jwtToken);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            UserDetails userDetails =this.customUserDetailService.loadUserByUsername(userName);
            //security
            if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else
                System.out.println("Token not validated..");

        }
        System.out.println(userName);
        HttpServletRequest httpServletRequest = request;
        httpServletRequest.setAttribute("username", userName);
        filterChain.doFilter(httpServletRequest,response);
    }
}
