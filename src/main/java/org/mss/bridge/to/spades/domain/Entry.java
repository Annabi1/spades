package org.mss.bridge.to.spades.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Entry {

	@JacksonXmlProperty(localName="key",isAttribute = true)
    private String key;
	
	@JacksonXmlProperty(localName="value",isAttribute = true)
    private String value;
	
	
	 @JacksonXmlProperty(localName = "ref")
	 @JacksonXmlCData
	 @JacksonXmlElementWrapper(useWrapping = false)
	    private Ref ref;
	 
	 @JacksonXmlProperty(localName = "map")
	 @JacksonXmlCData
	 @JacksonXmlElementWrapper(useWrapping = false)
	    private Map map;
	 
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Ref getRef() {
		return ref;
	}
	public void setRef(Ref ref) {
		this.ref = ref;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	 
	 
}

