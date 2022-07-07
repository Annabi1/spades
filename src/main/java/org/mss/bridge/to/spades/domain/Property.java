package org.mss.bridge.to.spades.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Property {
	 	@JacksonXmlProperty(localName="name",isAttribute = true)
	    private String name;
	    @JacksonXmlProperty(localName="value",isAttribute = true)
	    private String value;
	    @JacksonXmlProperty(localName="ref",isAttribute = true)
	    private String ref;
	    @JacksonXmlProperty(localName="map")
	    @JacksonXmlCData
		@JacksonXmlElementWrapper(useWrapping = false)
		private Map map;
	    
	    
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getRef() {
			return ref;
		}
		public void setRef(String ref) {
			this.ref = ref;
		}
		public Map getMap() {
			return map;
		}
		public void setMap(Map map) {
			this.map = map;
		}
	    
	    
	    
}
