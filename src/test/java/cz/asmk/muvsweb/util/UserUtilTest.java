package cz.asmk.muvsweb.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class UserUtilTest {

	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void validateNames(){
		String firstNameValid = "Abraham";
		String firstNameError = "leonidas";
		String lastNameValid = "Kopretina";
		String lastNameError = "$%&&&*E%^!*";
		Exception exception = null;
		try {
			UserUtil.validateFirstName(firstNameValid);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			UserUtil.validateFirstName(firstNameError);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			UserUtil.validateLastName(lastNameValid);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			UserUtil.validateLastName(lastNameError);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}


	@Test
	@Tag("UnitTest")
	@Tag("UtilTest")
	void validatePhneAndMail(){
		String phoneVaid = "321654987";
		String phoneError = "/*-456aha";
		String emailValid = "jana@krupicova.cz";
		String emailError = "%ana@@ana.ap";
		Exception exception = null;
		try {
			UserUtil.checkPhoneNumber(phoneVaid);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			UserUtil.checkPhoneNumber(phoneError);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			UserUtil.checkEmail(emailValid);
		} catch (Exception e){
			exception = e;
		}
		assertNull(exception);
		try {
			UserUtil.checkEmail(emailError);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}
}