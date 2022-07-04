package org.mss.bridge.to.spades.repository;

import org.mss.bridge.to.spades.domain.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ScenarioRepository  extends JpaRepository <Scenario , String>{

	void save (String sccenario );

	
}