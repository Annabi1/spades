package org.mss.bridge.to.spades.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;



@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Bean {
	@JacksonXmlProperty(localName="id",isAttribute = true)
    private String id;
	
	@JacksonXmlProperty(localName="class",isAttribute = true)
    private String classB;
	
	 @JacksonXmlProperty(localName = "property")
	 @JacksonXmlCData
	 @JacksonXmlElementWrapper(useWrapping = false)
	    private List<Property> property;

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassB() {
		return classB;
	}

	public void setClassB(String classB) {
		this.classB = classB;
	}
	 
	 
}
