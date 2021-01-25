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

import cz.asmk.muvsweb.entity.Event;
import cz.asmk.muvsweb.service.api.EventService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventServiceImplTest {

	@Autowired
	private EventService eventService;

	@Test
	@Tag("IntegrationTest")
	void testEvent() throws ParseException {
		Event event = new Event();
		event.setName("TestEvent");
		event.setLength("1,5h");
		Exception exception = null;
		try {
			event.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1254"));
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		event.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
		event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/02/2020"));
		Event save = eventService.save(event);
		Event event1 = eventService.get(save.getId());
		assertNotNull(event1);
		assertNotNull(save);
		assertFalse(eventService.findAll().isEmpty());
		eventService.delete(event1.getId());
		assertTrue(eventService.findAll().isEmpty());
	}

}