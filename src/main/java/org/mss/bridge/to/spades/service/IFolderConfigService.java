package org.mss.bridge.to.spades.service;
import org.mss.bridge.to.spades.domain.FolderConfig;


import java.util.List;
import java.util.Optional;


public interface IFolderConfigService {
	public 	List<FolderConfig> findAll();
	public FolderConfig save(FolderConfig ffolderConfig);
	public void delete(String id);
	public Optional<FolderConfig> getFolderConfigById(String id);
    public FolderConfig updateFolderConfig(FolderConfig folderConfig,String id);

}
