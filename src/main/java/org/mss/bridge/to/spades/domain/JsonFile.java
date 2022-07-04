package org.mss.bridge.to.spades.domain;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

@Entity
@Table(name = "json_file")
public class JsonFile {
	
	 @Id
	    @GeneratedValue(generator = "uuid")
	    @GenericGenerator(name = "uuid", strategy = "uuid2")
	    @Column(length = 64)
	    private String id;

	    private String name;

	    @ElementCollection
	    private List<JSONObject> data;

	    
		public JsonFile() {
			
		}


		public JsonFile(String name, List<JSONObject> file) {
			this.name = name;
			this.data = file;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public List<JSONObject> getData() {
			return data;
		}


		public void setData(List<JSONObject> data) {
			this.data = data;
		}

	    
	  
}
