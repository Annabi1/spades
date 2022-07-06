package org.mss.bridge.to.spades.security.repository;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
   Optional< UserDetails> loadUserByUsername(String username) throws UsernameNotFoundException;

}
