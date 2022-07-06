package org.mss.bridge.to.spades.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "forms")

@ToString
public class Forms implements Serializable {

		    private static final long serialVersionUID = 1L;
			@Id
			@Column(name = "id_form", unique = true, nullable = false)
			private String id_form;
			@Column(name = "form_path", unique = true, nullable = true)
			private String form_path;
			@OneToMany(mappedBy="forms")
		    private Set<Scenario> scenarios;
			@OneToMany(mappedBy="form")
		    private Set<Flux> fux;
			public String getId_form() {
				return id_form;
			}
			public void setId_form(String id_form) {
				this.id_form = id_form;
			}
			public String getForm_path() {
				return form_path;
			}
			public void setForm_path(String form_path) {
				this.form_path = form_path;
			}
			public Forms(String id_form, String form_path) {
				super();
				this.id_form = id_form;
				this.form_path = form_path;
			}
			public Forms() {
			
			}
			

			
}
