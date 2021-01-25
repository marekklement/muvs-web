package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.SocialNetwork;
import cz.asmk.muvsweb.service.api.SocialNetworkService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SocialNetworkServiceImplTest {

	@Autowired
	private SocialNetworkService socialNetworkService;

	@Test
	@Tag("IntegrationTest")
	void testEvent() throws ParseException {
		SocialNetwork socialNetwork = new SocialNetwork();
		socialNetwork.setName("TestName");
		Exception exception = null;
		try {
			socialNetwork.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1254"));
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		socialNetwork.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		SocialNetwork save = socialNetworkService.save(socialNetwork);
		SocialNetwork socialNetwork1 = socialNetworkService.get(save.getId());
		assertNotNull(socialNetwork1);
		assertNotNull(save);
		assertFalse(socialNetworkService.findAll().isEmpty());
		socialNetworkService.delete(socialNetwork1.getId());
		assertTrue(socialNetworkService.findAll().isEmpty());
	}

}