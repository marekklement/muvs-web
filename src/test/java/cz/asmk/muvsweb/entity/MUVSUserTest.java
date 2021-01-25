package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class MUVSUserTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/User-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewUser(String username, String firstName, String lastname, String phone, String email, String result){
		boolean res = Boolean.valueOf(result);
		Exception ex = null;
		MUVSUser user = null;
		try {
			user = new MUVSUser(username,firstName,lastname,email,phone,null);
		} catch (Exception e){
			ex = e;
		}
		if(res){
			assertNull(ex);
			assertNotNull(user);
			assertEquals(user.getEmail(), email);
			assertEquals(user.getFirstName(),firstName);
			assertEquals(user.getUsername(), username);
			assertEquals(user.getLastName(), lastname);
			assertEquals(user.getPhoneNumber(),phone);
		} else {
			assertNotNull(ex);
			assertNull(user);
		}
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/User-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewUserSetters(String username, String firstName, String lastname, String phone, String email,
						  String result){
		boolean res = Boolean.valueOf(result);
		Exception ex = null;
		MUVSUser user = null;
		try {
			user = new MUVSUser();
			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastname);
			user.setPhoneNumber(phone);
			user.setEmail(email);
		} catch (Exception e){
			ex = e;
		}
		if(res){
			assertNull(ex);
			assertNotNull(user);
			assertEquals(user.getEmail(), email);
			assertEquals(user.getFirstName(),firstName);
			assertEquals(user.getUsername(), username);
			assertEquals(user.getLastName(), lastname);
			assertEquals(user.getPhoneNumber(),phone);
		} else {
			assertNotNull(ex);
		}
	}

}