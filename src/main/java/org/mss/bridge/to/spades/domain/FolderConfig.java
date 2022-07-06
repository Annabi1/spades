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

		@OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "id_scenario", referencedColumnName = "id_scenario")
		private Scenario scenarios;
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
			return scenarios;
		}
		public void setScenario(Scenario scenario) {
			this.scenarios = scenario;
		}
		public String getXsl_skeleton() {
			return xsl_skeleton;
		}
		public void setXsl_skeleton(String xsl_skeleton) {
			this.xsl_skeleton = xsl_skeleton;
		}
		public FolderConfig(String activity, Profils profils, Scenario scenarios, String xsl_skeleton) {
			super();
			this.activity = activity;
			this.profils = profils;
			this.scenarios = scenarios;
			this.xsl_skeleton = xsl_skeleton;
		}
		

}
