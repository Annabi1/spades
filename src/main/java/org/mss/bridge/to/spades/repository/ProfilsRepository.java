package org.mss.bridge.to.spades.repository;

import java.util.Optional;


import org.mss.bridge.to.spades.domain.Profils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface ProfilsRepository extends JpaRepository<Profils,String> {
	void save (String profil );

}
