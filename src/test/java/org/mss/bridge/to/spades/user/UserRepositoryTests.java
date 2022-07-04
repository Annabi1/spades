package org.mss.bridge.to.spades.user;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.mss.bridge.to.spades.domain.Role;
import org.mss.bridge.to.spades.domain.User;
import org.mss.bridge.to.spades.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;


@DataJpaTest(showSql=false)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	private UserRepository repo;
	// provided by spring jpa for uni testing
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	public UserRepositoryTests(UserRepository repo, TestEntityManager entityManager) {
		super();
		this.repo = repo;
		this.entityManager = entityManager;
	}
	
	  @Test 
	  public void testCreateNewUserWithOneRole() { 
		  Role roleAdmin=entityManager.find(Role.class, 1); 
	  PasswordEncoder pass=new BCryptPasswordEncoder();
	  String passwordencoded=pass.encode("mabrouka");
	  User userNameHM =new User("annabi.mabroukaa999@gmail.com",passwordencoded,"annab mabrouka");
	  userNameHM.getRoles().add(roleAdmin);
	  User savedUser = repo.save(userNameHM);
	  
	  }
	  @Test
		public void testUpdateUserDetails() {
			User userNam = repo.findById(1).get();
	        repo.save(userNam);
			System.out.println(userNam);
	       
		}
		 
	  
	 
}
