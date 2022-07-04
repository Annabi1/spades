package org.mss.bridge.to.spades.repository;

import java.util.Optional;

import org.mss.bridge.to.spades.domain.Forms;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;




public interface FormsRepository extends JpaRepository <Forms , String>{

	void save (String forms );

	

	
}