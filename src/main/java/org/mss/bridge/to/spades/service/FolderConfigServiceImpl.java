package org.mss.bridge.to.spades.service;




import java.util.List;
import java.util.Optional;

import org.mss.bridge.to.spades.domain.FolderConfig;
import org.mss.bridge.to.spades.repository.FolderConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FolderConfigServiceImpl implements IFolderConfigService {
    @Autowired
    private FolderConfigRepository folderRepo;
    	@Override
	public List<FolderConfig> findAll( ) {
		// TODO Auto-generated method stub
		return folderRepo.findAll();
	}

	@Override
	public FolderConfig save(FolderConfig ffolderConfig) {
		// TODO Auto-generated method stub
		return folderRepo.save(ffolderConfig);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		folderRepo.deleteById(id);
	}
	@Override
	public Optional<FolderConfig> getFolderConfigById(String id) {
		// TODO Auto-generated method stub
		return folderRepo.findById(id);
	}

	@Override
	public FolderConfig updateFolderConfig(FolderConfig folderConfig,String id) {
		Optional<FolderConfig> FolderConfigHCreated=folderRepo.findById(id);
		FolderConfigHCreated.get().setActivity(folderConfig.getActivity());
		FolderConfigHCreated.get().setProfils(folderConfig.getProfils());
		FolderConfigHCreated.get().setScenario(folderConfig.getScenario());
		folderConfig=FolderConfigHCreated.get();

		return folderRepo.save(folderConfig);
	}
	
    

}
