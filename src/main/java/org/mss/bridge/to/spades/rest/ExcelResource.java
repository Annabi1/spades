package org.mss.bridge.to.spades.rest;

import org.mss.bridge.to.spades.service.JsonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	private JsonFileService jsonFileService;
	 @PostMapping("excels") public String getExcel(@RequestParam("file")
	  MultipartFile file) { 
		  String nameJson=StringUtils.cleanPath(file.getOriginalFilename());;
	  System.out.println("th excel file name1111 is : "+nameJson);
	  
	  return jsonFileService.excel2Json(file,nameJson ); }
	 

}
