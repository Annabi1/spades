package org.mss.bridge.to.spades.domain;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name ="profils_scenarios")
public class ProfilScenario {

	
	 private String id_scenario;
	 

	 @Id
	 private String id_profil;

		public String getId_scenario() {
			return id_scenario;
		}

		public void setId_scenario(String id_scenario) {
			this.id_scenario = id_scenario;
		}

		public String getId_profil() {
			return id_profil;
		}

		public void setId_profil(String id_profil) {
			this.id_profil = id_profil;
		}

		public ProfilScenario(String id_scenario, String id_profil) {
		
			this.id_scenario = id_scenario;
			this.id_profil = id_profil;
		}

		public ProfilScenario() {
			
		}

		
		
	
}
