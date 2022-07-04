package org.mss.bridge.to.spades.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "flux")

@ToString
public class Flux  implements Serializable {
	 private static final long serialVersionUID = 1L;
		@Id
		@Column(name = "id_flux", unique = true, nullable = false)
		private String id_flux;
		@Column(name = "description_flux", unique = true, nullable = true)
		private String description_flux;
		@Column(name = "initiateur", unique = true, nullable = true)
		private boolean initiateur;
		
		@Column(name = "flux_suivant", unique = true, nullable = true)
		private String flux_suivant;
		@Column(name = "squelette_xsl", unique = true, nullable = true)
		private String squelette_xsl;
		@ManyToOne
	    @JoinColumn(name="id_form", nullable=true)
	    private Forms form;
		@ManyToOne
	    @JoinColumn(name="type_emetteur", nullable=true)
	    private Profils type_emetteur;
		@ManyToOne
	    @JoinColumn(name="type_destinataire", nullable=true)
	    private Profils type_destinataire;
		
		public Profils getType_emetteur() {
			return type_emetteur;
		}
		public void setType_emetteur(Profils type_emetteur) {
			this.type_emetteur = type_emetteur;
		}
		public Profils getType_destinataire() {
			return type_destinataire;
		}
		public void setType_destinataire(Profils type_destinataire) {
			this.type_destinataire = type_destinataire;
		}
		
		public String getSquelette_xsl() {
			return squelette_xsl;
		}
		public void setSquelette_xsl(String squelette_xsl) {
			this.squelette_xsl = squelette_xsl;
		}
		@ManyToOne
	    @JoinColumn(name="type_scenario", nullable=true)
	    private Scenario scenarios;
		
		public String getId_flux() {
			return id_flux;
		}
		public void setId_flux(String id_flux) {
			this.id_flux = id_flux;
		}
		public String getDescription_flux() {
			return description_flux;
		}
		public void setDescription_flux(String description_flux) {
			this.description_flux = description_flux;
		}
		public boolean isInitiateur() {
			return initiateur;
		}
		public void setInitiateur(boolean initiateur) {
			this.initiateur = initiateur;
		}
		
		public String getFlux_suivant() {
			return flux_suivant;
		}
		public void setFlux_suivant(String flux_suivant) {
			this.flux_suivant = flux_suivant;
		}
		public Forms getForm() {
			return form;
		}
		public void setForm(Forms form) {
			this.form = form;
		}
		public Scenario getScenarios() {
			return scenarios;
		}
		public void setScenarios(Scenario scenarios) {
			this.scenarios = scenarios;
		}
		public Flux() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Flux(String id_flux, String description_flux, boolean initiateur, String flux_suivant,
				String squelette_xsl, Forms form, Profils type_emetteur, Profils type_destinataire, Scenario scenarios) {
			super();
			this.id_flux = id_flux;
			this.description_flux = description_flux;
			this.initiateur = initiateur;
			this.flux_suivant = flux_suivant;
			this.squelette_xsl = squelette_xsl;
			this.form = form;
			this.type_emetteur = type_emetteur;
			this.type_destinataire = type_destinataire;
			this.scenarios = scenarios;
		}
	
		
		
		
	

}
