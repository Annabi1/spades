package org.mss.bridge.to.spades.service;




import java.util.ArrayList;

import java.util.List;


import java.util.Optional;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.repository.FormsRepository;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.mss.bridge.to.spades.repository.ProfilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
@Transactional
public class ProfilsServiceImpl  implements IProfilsService{
	@Autowired
	private ProfilsRepository profilsRepo;


	@Override
	public Profils save(Profils profils) {
		// TODO Auto-generated method stub
		return profilsRepo.save(profils);
	}

	@Override
	public List<Profils> findAll() {
		// TODO Auto-generated method stub
		return   profilsRepo.findAll( );
	}

	@Override
	public void delete(String id) {
		Profils profil=profilsRepo.getOne(id);
		if(profil.getId_profil()==id)
		{
			profilsRepo.deleteById(id);

		}
	}
	
       	
    	 
    	
    	 

			/*
			 * public List<String> getAllActors (Actors actor ){ List<Actors> liste=new
			 * ArrayList<>();
			 */ 
     
     
   
	
	

	@Override
	public Optional<Profils> getProfilById(String id) {
		// TODO Auto-generated method stub
		return profilsRepo.findById(id);
	}

	@Override
	public Profils updateProfil(String id,Profils profil) {
		
		Optional<Profils> profilupdated=profilsRepo.findById(id);
		profilupdated.get().setId_profil(profil.getId_profil());
		profilupdated.get().setDescription_profil(profil.getDescription_profil());
		profil=profilupdated.get();
		return profilsRepo.save(profil);}
	

	@Autowired
	FormsRepository profilRepository;
	
	@Autowired
	ProfilsRepository profilsscenariosrepo ;
	
	@Autowired
	JsonFileService fileService ;
	@Autowired
    JsonFileRepository  jsonFileRepository ;
	
	
	public JsonFile getScenarioProfil(String name) {
		String dataFile = name+".xlsx_Actors";
		JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);
		           

		       return scenario;
		   }
	
	
	
    public void saveProfil(String idscenario, JsonFile file ) {
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	list = file.getData();
    	List<String> list2 = new ArrayList<String>();
    	
    	

    	for(int i = 0 ; i < list.size() ; i++){
    	JSONObject obj = new JSONObject(list.get(i));
    	String attDef = String.valueOf(obj.get("DEFINITION"));
    	String attName = String.valueOf(obj.get("NAME"));
    	String name =attName.replaceAll(" ","_");
    //	String def =attDef.replaceAll(" ","_");
    	
    
    	
    	Profils profils=new Profils(name,attDef);
    	
    	profilsRepo.save(profils);
    	
    	
        }
    	}
    	 
    	
    	 

			/*
			 * public List<String> getAllActors (Actors actor ){ List<Actors> liste=new
			 * ArrayList<>();
			 */ 
     
     
     public List<Profils> getAllActors()
     {
         return (List<Profils>)
        		 profilsRepo.findAll();
     }
   
    

	
	


}
