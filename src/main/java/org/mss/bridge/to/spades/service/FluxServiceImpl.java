package org.mss.bridge.to.spades.service;




import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.Flux;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.repository.FluxRepository;
import org.mss.bridge.to.spades.repository.FormsRepository;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.mss.bridge.to.spades.repository.ProfilsRepository;
import org.mss.bridge.to.spades.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class FluxServiceImpl implements IFluxService {
	@Autowired
	private FluxRepository fluxRepo;
	
	@Autowired
	private ProfilsRepository profilsrepo;
	
	
	@Autowired
	private FormsRepository formsrepo;
	
	@Autowired
	private ScenarioRepository scenariorepo;

	@Override
	public List<Flux> findAll() {
		// TODO Auto-generated method stub
		return fluxRepo.findAll();
	}

	@Override
	public Flux save(Flux flux) {
		// TODO Auto-generated method stub
		return fluxRepo.save(flux);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		fluxRepo.deleteById(id);
	}

	@Override
	public Optional<Flux> getFluxById(String id) {
		// TODO Auto-generated method stub
		return fluxRepo.findById(id);
	}

	@Override
	public Flux updateFlux(Flux flux) {
     Flux fluxUpdated=new Flux();
     fluxUpdated=flux;
     return fluxRepo.save(fluxUpdated);

     
	}
	
	@Autowired
	JsonFileService fileService ;
	@Autowired
    JsonFileRepository  jsonFileRepository ;
	
	
	public JsonFile getScenarioFlux(String name) {
		String dataFile = name+".xlsx_Flows";
		JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);
		           

		       return scenario;
		   }
	public JsonFile getBusinesFlux(String name) {
		String processfile= name+".xlsx_Business process";
		JsonFile scenario = jsonFileRepository.getScenarioData(processfile);

		       return scenario;
		   }
	
	
    public void saveForm(String idscenario ,JsonFile file, JsonFile file1 ) {
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	List<JSONObject> list1 = new ArrayList<JSONObject>();

    	list = file.getData();
    	list1 = file.getData();
    	List<String> list2 = new ArrayList<String>();
    	
    	
    	for(int i = 0 ; i < list.size() ; i++){
    	  	
        	
        	Boolean type=false ;

    	JSONObject obj = new JSONObject(list.get(i)); 
    

    	String attDef = String.valueOf(obj.get("DEFINITION"));
    	String attName = String.valueOf(obj.get("ID"));
    	//String attType = String.valueOf(obj.get("Type"));
        String id_form= String.valueOf(obj.get("NAME"));
        String type_emetteur= String.valueOf(obj.get("SENDER"));
        String initateur=String.valueOf(obj.get("TYPE"));
        if (initateur.equals("Initiating"))
        {   type=true;
        	}
   

        
        String type_destinataire=String.valueOf(obj.get("RECEIVER"));
        String flux_suivant=String.valueOf(obj.get("NEXT FLOWS"));
        String squelette_xsl=null;
        String types_dest_copie=null;
        //        
         String idform=id_form.replaceAll(" ","_");
         String typedes=type_destinataire.replaceAll(" ", "_");
         String typeemetteur=type_emetteur.replaceAll(" ", "_");
         for(int j = 0 ; j < list1.size() ; j++){	
        	 JSONObject obj1 = new JSONObject(list1.get(j)); 
             String type_scenario= String.valueOf(obj1.get("Process Name"));;

        	 
    	Flux flux=new Flux();
    	flux.setDescription_flux(attDef);
    	flux.setId_flux(attName);
    	flux.setType_emetteur(profilsrepo.getById(typeemetteur));
       	flux.setType_destinataire(profilsrepo.getById(typedes));
       	flux.setForm(formsrepo.getById(idform));
       	flux.setInitiateur(type);
       	flux.setFlux_suivant(flux_suivant);
       	flux.setScenarios(scenariorepo.getById(idscenario));
       	
    	fluxRepo.save(flux);
        }
    	}
    }	
    	 
	

}
