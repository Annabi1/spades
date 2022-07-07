package org.mss.bridge.to.spades.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;



@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Map {

	 @JacksonXmlProperty(localName = "entry")
	 @JacksonXmlCData
	 @JacksonXmlElementWrapper(useWrapping = false)
	    private List<Entry> entry;

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
	 
	 
}