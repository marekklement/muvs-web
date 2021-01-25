package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LinkTest {

	@ParameterizedTest
	@CsvSource("ExampleName,some/url/yes,06/01/2020")
	@Tag("UnitTest")
	void testLinkSetters(String name, String url, String date) throws ParseException {
		Exception exception = null;
		Link link = null;
		try {
			link = new Link();
			link.setName(name);
			link.setUrl(url);
			link.setCreated(new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertNotNull(link);
		assertEquals(link.getName(), name);
		assertEquals(link.getUrl(),url);
		assertNotNull(link.getCreated());
	}

	@ParameterizedTest
	@CsvSource("ExampleName,some/url/yes,06/01/2020")
	@Tag("UnitTest")
	void testLinkConstructor(String name, String url, String date) throws ParseException {
		Exception exception = null;
		Link link = null;
		try {
			link = new Link(url,name,new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertNotNull(link);
		assertEquals(link.getName(), name);
		assertEquals(link.getUrl(),url);
		assertNotNull(link.getCreated());
	}

}