package org.mss.bridge.to.spades.profils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.repository.ProfilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
@DataJpaTest(showSql=false)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class profilsTestsRepository {

	@Autowired
	private ProfilsRepository repo;
	@Test
	public void testCreateFirstProfil() {
		Profils profil = new Profils("user","manage everything");
		Optional<Profils> profils =repo.findById(profil.getId_profil());
	
			Profils profilSaved = repo.save(profil);
			
		
		/*The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an expected one.*/
		assertThat(profilSaved.getId_profil()).hasSizeGreaterThan(0);}
	
}
