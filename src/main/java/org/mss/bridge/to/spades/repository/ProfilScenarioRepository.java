package org.mss.bridge.to.spades.repository;

import org.mss.bridge.to.spades.domain.ProfilScenario;
import org.springframework.data.jpa.repository.JpaRepository
;
import org.springframework.stereotype.Repository;


public interface ProfilScenarioRepository 
	extends JpaRepository <ProfilScenario , Long>{

		void save (String profil );

}