package cz.asmk.muvsweb.util;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateUtilTest {

	@ParameterizedTest
	@ValueSource(strings = {"25/06/1920", "01/01/1254"})
	@Tag("UnitTest")
	@Tag("UtilTest")
	void testDateWrong(String form) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(form);
		Exception exception = null;
		try {
			DateUtil.validationOfDate(date);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}

	@ParameterizedTest
	@ValueSource(strings = {"25/06/2020", "01/01/2021"})
	@Tag("UnitTest")
	@Tag("UtilTest")
	void testDateOk(String form) throws ParseException {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(form);
		Exception exception = null;
		boolean bool = false;
		try {
			bool = DateUtil.validationOfDate(date);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		assertTrue(bool);
	}

	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void testBetween() throws ParseException {
		String inside = "06/12/2020";
		Date insideDate = new SimpleDateFormat("dd/MM/yyyy").parse(inside);
		String outside = "06/12/2018";
		Date outsideDate = new SimpleDateFormat("dd/MM/yyyy").parse(outside);
		//
		String from = "07/12/2018";
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(from);
		String to = "07/12/2020";
		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(to);
		Exception exception = null;
		try {
			DateUtil.validationOfDate(null);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		assertTrue(DateUtil.checkDate(insideDate, fromDate, toDate));
		assertFalse(DateUtil.checkDate(outsideDate, fromDate, toDate));
	}
}