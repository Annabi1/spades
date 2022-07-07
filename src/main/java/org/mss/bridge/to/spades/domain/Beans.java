package org.mss.bridge.to.spades.domain;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;



@JacksonXmlRootElement(localName = "beans")
public class Beans {

	 @JacksonXmlProperty(localName = "bean")
	 @JacksonXmlCData
	 @JacksonXmlElementWrapper(useWrapping = false)
	    private List<Bean> bean;

	public List<Bean> getBean() {
		return bean;
	}

	public void setBean(List<Bean> bean) {
		this.bean = bean;
	}
	 

}
