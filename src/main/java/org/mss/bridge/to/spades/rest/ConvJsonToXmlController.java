package org.mss.bridge.to.spades.rest;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.mss.bridge.to.spades.service.JsonToXmlServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin("*")
@RestController
public class ConvJsonToXmlController {

	@Autowired
	JsonToXmlServiceImp jsonToXmlService;
	
	@GetMapping("json-xml")
	public String transform() throws JDOMException, IOException {
		
		String xmlData = jsonToXmlService.transformJsonToXML();
		return xmlData;
	}
}
