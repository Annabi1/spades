package org.mss.bridge.to.spades.repository;

import java.util.Optional;

import org.mss.bridge.to.spades.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository  extends JpaRepository<User,Integer>{
	Optional<UserDetails> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);}
