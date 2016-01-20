package org.woofenterprise.dogs.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.woofenterprise.dogs.dao.CustomerDAO;
import org.woofenterprise.dogs.entity.Customer;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by michal.babel on 20-Jan-16.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Customer customer = customerDAO.findByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDetails userDetails = new UserDetails() {

            final GrantedAuthority ROLE_USER = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "USER";
                }
            };
            final GrantedAuthority ROLE_ADMIN = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ADMIN";
                }
            };

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(ROLE_USER);
                if (customer.isAdmin()) {
                    grantedAuthorities.add(ROLE_ADMIN);
                }
                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return customer.getPasswordHash();
            }

            @Override
            public String getUsername() {
                return customer.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        return userDetails;
    }
}
