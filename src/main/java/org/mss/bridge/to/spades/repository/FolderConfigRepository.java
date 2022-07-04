package org.mss.bridge.to.spades.repository;

import org.mss.bridge.to.spades.domain.FolderConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FolderConfigRepository extends JpaRepository<FolderConfig, String> {
	void save (String folder );

}
