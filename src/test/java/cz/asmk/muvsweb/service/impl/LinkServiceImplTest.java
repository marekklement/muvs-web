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

import cz.asmk.muvsweb.entity.Link;
import cz.asmk.muvsweb.service.api.LinkService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LinkServiceImplTest {

	@Autowired
	private LinkService linkService;

	@Test
	@Tag("IntegrationTest")
	void testLink() throws ParseException {
		Link link = new Link();
		link.setName("TestLink");
		link.setUrl("test/url");
		Exception exception = null;
		try {
			link.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1254"));
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		link.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		Link save = linkService.save(link);
		Link link1 = linkService.get(save.getId());
		assertNotNull(link1);
		assertNotNull(save);
		assertFalse(linkService.findAll().isEmpty());
		linkService.delete(link1.getId());
		assertTrue(linkService.findAll().isEmpty());
	}

}