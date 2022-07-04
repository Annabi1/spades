package org.mss.bridge.to.spades.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "Scenarios")

@ToString
public class Scenario implements Serializable {
    private static final long serialVersionUID = 1L;
		@Id
		@Column(name = "id_scenario", unique = true, nullable = false)
		private String id_scenario;
		@Column(name = "desciption_scenario", unique = true, nullable = true)
		private String description_scenario;
		@ManyToOne
	    @JoinColumn(name="id_form_scenario", nullable=false)
	    private Forms forms;
		public String getId_scenario() {
			return id_scenario;
		}
		public void setId_scenario(String id_scenario) {
			this.id_scenario = id_scenario;
		}
		public String getDescription_scenario() {
			return description_scenario;
		}
		public void setDescription_scenario(String description_scenario) {
			this.description_scenario = description_scenario;
		}
		public Forms getForms() {
			return forms;
		}
		public void setForms(Forms forms) {
			this.forms = forms;
		}
		public Scenario(String id_scenario, String description_scenario, Forms forms) {
			super();
			this.id_scenario = id_scenario;
			this.description_scenario = description_scenario;
			this.forms = forms;
		}
		public Scenario() {
			super();
			// TODO Auto-generated constructor stub
		}
	
		
		
}
