package org.mss.bridge.to.spades.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Ref {
	@JacksonXmlProperty(localName="bean",isAttribute = true)
    private String bean;

	public String getBean() {
		return bean;
	}

	public void setBean(String bean) {
		this.bean = bean;
	}
	
	
}