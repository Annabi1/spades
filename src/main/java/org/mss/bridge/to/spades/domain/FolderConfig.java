package org.mss.bridge.to.spades.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "folder_content_user_activity_config")
@Data
@ToString
public class FolderConfig implements Serializable {
	 private static final long serialVersionUID = 1L;
		@Id
		@Column(name = "activity", unique = true, nullable = false)
		private String activity;
		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "actor", referencedColumnName = "id_profil")
		private Profils profils;

		private String next_activity;

		private String condition;
	   
		private String duration;
		
		private String type ;
		
		@Column(length = 2048)	
		private String defintion ;
		
		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "idScenario", referencedColumnName = "id_scenario")
		private Scenario idScenario;
		@Column(name = "xsl_skeleton", unique = true, nullable = true)
		private String xsl_skeleton;
		public FolderConfig() {
			super();
			// TODO Auto-generated constructor stub
		}
		public String getActivity() {
			return activity;
		}
		public void setActivity(String activity) {
			this.activity = activity;
		}
		public Profils getProfils() {
			return profils;
		}
		public void setProfils(Profils profils) {
			this.profils = profils;
		}
		public Scenario getScenario() {
			return idScenario;
		}
		public void setScenario(Scenario scenario) {
			this.idScenario = scenario;
		}
		public String getXsl_skeleton() {
			return xsl_skeleton;
		}
		public void setXsl_skeleton(String xsl_skeleton) {
			this.xsl_skeleton = xsl_skeleton;
		}
		
		public String getNext_activity() {
			return next_activity;
		}
		public void setNext_activity(String next_activity) {
			this.next_activity = next_activity;
		}
		public String getCondition() {
			return condition;
		}
		public void setCondition(String condition) {
			this.condition = condition;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getDefintion() {
			return defintion;
		}
		public void setDefintion(String defintion) {
			this.defintion = defintion;
		}
		public FolderConfig(String activity, Profils profils, Scenario scenarios, String xsl_skeleton) {
			super();
			this.activity = activity;
			this.profils = profils;
			this.idScenario = scenarios;
			this.xsl_skeleton = xsl_skeleton;
		}
		

}
