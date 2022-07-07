package org.mss.bridge.to.spades.service;




import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.FolderConfig;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.repository.FolderConfigRepository;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.mss.bridge.to.spades.repository.ProfilsRepository;
import org.mss.bridge.to.spades.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Transactional
@Service
public class FolderConfigServiceImpl implements IFolderConfigService {
    @Autowired
    private FolderConfigRepository folderRepo;
    
    @Autowired
    private ProfilsRepository profilsRepo;
    @Autowired
    private ScenarioRepository scenarioRepo;
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
	
	@Autowired
    JsonFileRepository  jsonFileRepository ;
	
	
	public JsonFile getScenarioFoldercontent(String name) {
		String dataFile = name+".xlsx_Actor-Activity";
		JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);
		           

		       return scenario;
		   }
	
	
	
    public void saveFoldercontent(String idscenario, JsonFile file ) {
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	list = file.getData();
    	
    	

    	for(int i = 0 ; i < list.size() ; i++){
    	JSONObject obj = new JSONObject(list.get(i));
    	String attActor = String.valueOf(obj.get("Actor"));
    	String attActtivity = String.valueOf(obj.get("Activity"));
    	String Actor =attActor.replaceAll(" ","_");
    	
    
    	
         FolderConfig	 folder=new FolderConfig();
         folder.setScenario(scenarioRepo.getById(attActor));
         folder.setActivity(attActtivity);
         folder.setProfils(profilsRepo.getById(Actor));
    	
         //folderrepo.save(folder);
    	
    	
        }
    	}
    public List<FolderConfig> getAllActivities()
    {
        return (List<FolderConfig>)
        		folderRepo.findAll();
    }
    
    public List<FolderConfig> getAllActivitiesByIdScenario(String idScenario)
    {
        return folderRepo.findByIdScenario(idScenario);
    }
    
    public FolderConfig getActivityByIdScenarioAndType(String idScenario,String type)
    {
        return folderRepo.findByIdScenarioAndType(idScenario,type);
    }
	
    	  
}
