package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.MUVSUser;
import cz.asmk.muvsweb.service.api.MUVSUserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MUVSUserServiceImplTest {

	@Autowired
	private MUVSUserService muvsUserService;

	@ParameterizedTest
	@CsvSource("panama123,Pavel,Kamana,kamara@mail.com,555444333,$%##@@,777apaak")
	@Tag("IntegrationTest")
	@Order(1)
	void testNewUser(String username, String firstname, String lastname, String email, String phone, String wrongMail
			, String wrongPhne){
		MUVSUser user = new MUVSUser();
		user.setUsername(username);
		user.setFirstName(firstname);
		user.setLastName(lastname);
		Exception exception = null;
		try {
			user.setEmail(wrongMail);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		user.setEmail(email);
		exception = null;
		try {
			user.setPhoneNumber(wrongPhne);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		user.setPhoneNumber(phone);
		MUVSUser save = muvsUserService.save(user);
		MUVSUser muvsUser = muvsUserService.get(save.getId());
		assertNotNull(muvsUser);
		assertEquals(save.getFirstName(), muvsUser.getFirstName());
	}

	@Test
	@Tag("IntegrationTest")
	@Order(2)
	void testUpdateUser(){
		String username = "panama123";
		String newLastname = "Petr";
		List<MUVSUser> all = muvsUserService.findAll();
		assertFalse(all.isEmpty());
		assertEquals(1,all.size());
		MUVSUser muvsUser = all.get(0);
		MUVSUser sameUser = muvsUserService.findByUsername(username);
		assertEquals(muvsUser.getFirstName(),sameUser.getFirstName());
		muvsUser.setLastName(newLastname);
		muvsUserService.update(muvsUser);
		MUVSUser lastUser = muvsUserService.findByUsername(username);
		assertEquals(newLastname, lastUser.getLastName());
	}

	@Test
	@Tag("IntegrationTest")
	@Order(3)
	void deleteAfter(){
		String username = "panama123";
		List<MUVSUser> all = muvsUserService.findAll();
		assertFalse(all.isEmpty());
		assertEquals(1,all.size());
		muvsUserService.delete(all.get(0).getId());
		all = muvsUserService.findAll();
		assertTrue(all.isEmpty());
	}
}