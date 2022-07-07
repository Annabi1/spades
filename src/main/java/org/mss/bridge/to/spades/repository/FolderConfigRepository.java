package org.mss.bridge.to.spades.repository;

import java.util.List;

import org.mss.bridge.to.spades.domain.FolderConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface FolderConfigRepository extends JpaRepository<FolderConfig, String> {
	void save (String folder );
public List<FolderConfig> findByIdScenario(String idScenaario);
	
	public FolderConfig findByIdScenarioAndType(String idScenaario,String type);
}
