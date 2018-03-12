package edu.mum.coffee.service;

import edu.mum.coffee.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    private PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new UsernameNotFoundException("Username is Null");
        }

        Person person = personService.findByEmail(username);
        UserDetailsImpl userDetail = new UserDetailsImpl();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if(username.equals("admin")){
            userDetail.setUsername("admin");
            userDetail.setPassword("admin");
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            userDetail.setAuthorities(grantedAuthorities);
            return userDetail;
        } else if(username.equals("user")){
            userDetail.setUsername("user");
            userDetail.setPassword("user");
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            userDetail.setAuthorities(grantedAuthorities);
            return userDetail;
        }


        grantedAuthorities.add(new SimpleGrantedAuthority(person.getRole()));

        userDetail.setUsername(username);
        userDetail.setPassword(person.getPassword());
        userDetail.setAuthorities(grantedAuthorities);
        userDetail.setEnabled(person.isEnable());
        System.out.println("User login info");
        System.out.println("username: " + userDetail.getUsername());
        System.out.println("password: " + userDetail.getPassword());
        System.out.println("role: " + userDetail.getAuthorities());

        return userDetail;

    }
}
