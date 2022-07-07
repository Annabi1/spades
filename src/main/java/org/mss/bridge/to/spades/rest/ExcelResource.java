package org.mss.bridge.to.spades.rest;

import java.util.List;



import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.service.FluxServiceImpl;
import org.mss.bridge.to.spades.service.FolderConfigServiceImpl;
import org.mss.bridge.to.spades.service.FormsServiceImpl;
import org.mss.bridge.to.spades.service.JsonFileService;
import org.mss.bridge.to.spades.service.ProfilsServiceImpl;
import org.mss.bridge.to.spades.service.ScenarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Flux",tags="REST - Flux")
public class ExcelResource {
	@Autowired
	JsonFileService jsonFileService;
	
	@Autowired
	FormsServiceImpl formsService;
	
    @Autowired
    ProfilsServiceImpl profilService ;
    
    @Autowired
    ScenarioServiceImpl scenarioServie ;
    
    @Autowired
    FluxServiceImpl fluxservvice ;
    
    @Autowired
    FolderConfigServiceImpl folderservice ;
	
	
	
	
	@PostMapping("config")
	public String Save(@RequestParam(value="") String config) {
		
		
		JsonFile profilJsonFile= profilService.getScenarioProfil(config);
		
		JsonFile formsJsonFile= formsService.getForms(config);
		JsonFile formJsonFile= formsService.getBusiness(config);

		
	JsonFile scenarioJsonFile= scenarioServie.getScenarios(config);
		
		JsonFile fluxJsonFile= fluxservvice.getScenarioFlux(config);
		JsonFile bfluxJsonFile= fluxservvice.getBusinesFlux(config);

		JsonFile FolderJsonFile= folderservice.getScenarioFoldercontent(config);
		String idscenario= formsService.saveForms(formJsonFile,formsJsonFile);
	       scenarioServie.saveScenario(idscenario, scenarioJsonFile);
		profilService.saveProfil(idscenario,profilJsonFile);
       fluxservvice.saveForm(idscenario ,fluxJsonFile,bfluxJsonFile);
       folderservice.saveFoldercontent(idscenario, FolderJsonFile); 
		
       return  ("test");
	}
 

	
	  @PostMapping("excels") public String getExcel(@RequestParam("file")
	  MultipartFile file) { 
		  String nameJson=StringUtils.cleanPath(file.getOriginalFilename());;
	  System.out.println("th excel file name1111 is : "+nameJson);
	  
	  return jsonFileService.excel2Json(file,nameJson ); }
	 

	
	@GetMapping("scenariosconfig")
	public List<JsonFile> getScenario(@RequestParam("scenarioName") String scenarioName) {
		
		List<JsonFile> scenario = jsonFileService.getScenario(scenarioName);
		return scenario;
	}
	
	@GetMapping("scenarios/data")
	public List<String> getScenarioData(@RequestParam("scenarioName") String scenarioName) {

	JsonFile scenarioData = jsonFileService.getScenarioData(scenarioName);
	List<String> strList = jsonFileService.processScenarioData(scenarioData);
	return strList;
	}
}

	
	
	
	
