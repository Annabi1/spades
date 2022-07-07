package org.mss.bridge.to.spades.service;

import java.util.ArrayList;

import java.util.List;


import java.util.Optional;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.domain.Scenario;
import org.mss.bridge.to.spades.repository.FormsRepository;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.mss.bridge.to.spades.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScenarioServiceImpl implements IScenarioService {
	@Autowired
      private   ScenarioRepository scenarioRepo;
      @Autowired
      private FormsRepository formsRepo;


	@Override
	public Scenario save(Scenario scenario) {
		// TODO Auto-generated method stub
		return  scenarioRepo.save(scenario);
	}



	@Override
	public List<Scenario> getAllScenario() {
		// TODO Auto-generated method stub
		return scenarioRepo.findAll();
	}



	@Override
	public void delete(String id) {
		Scenario scenario=scenarioRepo.getOne(id);
		if(scenario.getId_scenario()==id)
		{
		scenarioRepo.deleteById(id);		}
	}



	@Override
	public Optional<Scenario> getScenarioById(String id) {
		// TODO Auto-generated method stub
		return scenarioRepo.findById(id) ;
	}



	@Override
	public Scenario updateScenario(String id_scenario, Scenario scenario) {
		Optional<Scenario>  scenarioCreated=scenarioRepo.findById(id_scenario);
		scenarioCreated.get().setDescription_scenario(scenario.getDescription_scenario());
		scenarioCreated.get().setId_scenario(scenario.getId_scenario());
		scenarioCreated.get().setForms(scenario.getForms());
		scenario=scenarioCreated.get();
		scenarioRepo.save(scenario);
		return scenario;
	}
	@Autowired
	JsonFileService fileService ;
	@Autowired
    JsonFileRepository  jsonFileRepository ;
	
	
	public JsonFile getScenarios(String name) {
		String dataFile = name+".xlsx_Business process";
		JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);
		           

		       return scenario;
		   }
	
	
	
    public void saveScenario(String idscenario ,JsonFile file ) {
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	list = file.getData();
    	List<String> list2 = new ArrayList<String>();
    	
    	

    	for(int i = 0 ; i < list.size() ; i++){
    	JSONObject obj = new JSONObject(list.get(i));
    	
    	String attdesScenario= String.valueOf(obj.get("Process definition"));
    	String scenarioDef = String.valueOf(obj.get("Process name"));
    	//String id =attidScenario.replaceAll(" ","_");
    	//String formSc =idformScenario.replaceAll(" ","_");
    	String id =scenarioDef.replaceAll(" ","_");
    	
    	Scenario form=new Scenario();
    	form.setId_scenario(id);
    	form.setDescription_scenario(idscenario);
    	form.setDescription_scenario(attdesScenario);
    	form.setForms(formsRepo.getById(idscenario));
    	scenarioRepo.save(form);
        }
    	}
    	 



    public List<Scenario> getAllScenarios()
    {
        return (List<Scenario>)
        		scenarioRepo.findAll();
    }



}
