package org.mss.bridge.to.spades.repository;

import java.util.Optional;

import org.mss.bridge.to.spades.domain.Flux;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



public interface FluxRepository extends JpaRepository <Flux , String>{

	void save (String flux );


}