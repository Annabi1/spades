package org.mss.bridge.to.spades.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "profils")

public class Profils implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_profil", unique = true, nullable = false)
	private String id_profil;
	@Column(name = "description_profil", unique = true, nullable = true)
	private String description_profil;
	//Association between scenario and profils
	@OneToMany(mappedBy="form")
    private Set<Flux> fux;
	

	@ManyToMany
	@JoinTable(
	  name = "profils_scenarios", 
	  joinColumns = @JoinColumn(name = "id_scenario"), 
	  inverseJoinColumns = @JoinColumn(name = "id_profil"))
	Set<Scenario> scenarios;
	public Profils() {}
		public Profils(String id_profil, String description_profil) {
			this.id_profil=id_profil;
			this.description_profil=description_profil;
		// TODO Auto-generated constructor stub
	}
		public String getId_profil() {
		return id_profil;
	}
	public void setId_profil(String id_profil) {
		this.id_profil = id_profil;
	}
	public String getDescription_profil() {
		return description_profil;
	}
	public void setDescription_profil(String description_profil) {
		this.description_profil = description_profil;
	}
	
	
	
	
	
}
