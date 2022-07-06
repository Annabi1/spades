package org.mss.bridge.to.spades.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mss.bridge.to.spades.domain.ERole;
import org.mss.bridge.to.spades.domain.Role;
import org.mss.bridge.to.spades.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;



@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class RoleTestsRepository {
	@Autowired
	private RoleRepository repo;
	@Test
	public void testCreateRestRoles() {
		
		Role admin = new Role(ERole.ROLE_ADMIN);
		Role user = new Role(ERole.ROLE_USER);
	}

}
