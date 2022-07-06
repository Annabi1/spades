package org.mss.bridge.to.spades.service;




import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.Forms;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.repository.FormsRepository;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
@Transactional
public class FormsServiceImpl implements IFormsService {
	@Autowired
	private FormsRepository formsRepo;
	
	@Override
	public List<Forms> findAll() {
		// TODO Auto-generated method stub
		return formsRepo.findAll();
	}

	@Override
	public Forms save(Forms forms) {
		// TODO Auto-generated method stub
		return formsRepo.save(forms);
	}

	public void delete(String id) {
Forms form=	formsRepo.getOne(id);
if(form.getId_form()==id) {
	formsRepo.deleteById(id);
}

	}

	@Override
	public Optional<Forms> getFormById(String id) {
		// TODO Auto-generated method stub
		return formsRepo.findById(id);
	}

	@Override
    public Forms updateForm(Forms form,String id) {
		Optional<Forms> formSearch=formsRepo.findById(id);
			formSearch.get().setId_form(form.getId_form());
			formSearch.get().setForm_path(form.getForm_path());
			form=formSearch.get();
			formsRepo.save(form);
		
		return form;
	}
	@Autowired
	JsonFileService fileService ;
	@Autowired
    JsonFileRepository  jsonFileRepository ;
	
	
	public JsonFile getForms(String name) {
		String dataFile = name+".xlsx_Flows";
		JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);

		       return scenario;
		   }
	
	public JsonFile getBusiness(String name) {
		String processfile= name+".xlsx_Business process";
		JsonFile scenario = jsonFileRepository.getScenarioData(processfile);

		       return scenario;
		   }
	
	
    public String saveForms(JsonFile file ,JsonFile file2 ) {
    	List<JSONObject> list = new ArrayList<JSONObject>();
    	List<JSONObject> list2 = new ArrayList<JSONObject>();
    	String idscenario=null;
    	list=file.getData();
    	list2=file2.getData();    	

    	for(int i = 0 ; i < list.size() ; i++){
    	JSONObject obj = new JSONObject(list.get(i));
    	String attFormPath=null;
    	String attName = String.valueOf(obj.get("Process name"));
         idscenario =attName.replaceAll(" ","_");
    	Forms form=new Forms(idscenario,attFormPath);
    	formsRepo.save(form);
    	}
    
    	for(int i = 0 ; i < list2.size() ; i++){
    	JSONObject obj = new JSONObject(list2.get(i));
    	String attFormPath=null;
    	String attName = String.valueOf(obj.get("NAME"));
         String name =attName.replaceAll(" ","_");
    	
    	Forms form=new Forms();
    	form.setId_form(name);
    	form.setForm_path(attFormPath);
    	formsRepo.save(form);
       }
    	return idscenario;
    	}
    
    	
	}
