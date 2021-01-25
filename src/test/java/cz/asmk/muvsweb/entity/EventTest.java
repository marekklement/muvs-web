package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EventTest {

	@ParameterizedTest
	@CsvSource("ExampleName,06/01/1919,07/02/1212,1-5h,sample")
	@Tag("UnitTest")
	void oldDate(String name, String date, String created, String length, String description) throws ParseException {
		Event event = new Event();
		event.setName(name);
		event.setLength(length);
		event.setDescription(description);
		Exception exception = null;
		try {
			event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			event.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse(created));
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}

	@ParameterizedTest
	@CsvSource("ExampleName,06/01/2020,07/02/2020,1-5h,sample")
	@Tag("UnitTest")
	void newDate(String name, String date, String created, String length, String description) throws ParseException {
		Event event = new Event();
		event.setName(name);
		event.setLength(length);
		event.setDescription(description);
		Exception exception = null;
		try {
			event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			event.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse(created));
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
	}

	@ParameterizedTest
	@CsvSource("ExampleName,06/01/2020,07/02/2020,1-5h,sample")
	@Tag("UnitTest")
	void same(String name, String date, String created, String length, String description) throws ParseException {
		Exception exception = null;
		Event event = null;
		try {
			event = new Event();
			event.setName(name);
			event.setLength(length);
			event.setDescription(description);
			event.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
			event.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse(created));
			event.setId(1);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertNotNull(event);
		Event event2 = null;
		try {
			event2 = new Event(name,new SimpleDateFormat("dd/MM/yyyy").parse(created),new SimpleDateFormat("dd/MM" +
					"/yyyy").parse(date),length,description);
			event2.setId(1);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertNotNull(event2);
		assertEquals(event,event2);
	}
}