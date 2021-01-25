package cz.asmk.muvsweb.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class LengthUtilTest {

	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void testCheckForSomeLocations(){
		Exception exception = null;
		try {
			LengthUtil.checkIfSomeLocationSet("titleCz", "titleEn");
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			LengthUtil.checkIfSomeLocationSet("", "titleEn");
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			LengthUtil.checkIfSomeLocationSet("titleCz", null);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			LengthUtil.checkIfSomeLocationSet("", "");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			LengthUtil.checkIfSomeLocationSet(null, null);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			LengthUtil.checkIfSomeLocationSet(null, "");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}


	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void testForLength(){
		String titleShort = "b";
		String titleOk = "Title ok";
		String titleLong = "To long title 5555555555555555555555555555555555555555555555555555555";
		Exception exception = null;
		try {
			LengthUtil.checkTitleLength(titleOk,titleOk);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			LengthUtil.checkTitleLength(titleShort,titleOk);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			LengthUtil.checkTitleLength(titleOk,titleLong);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}
}