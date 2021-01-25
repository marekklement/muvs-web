package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class SocialNetworkTest {

	@ParameterizedTest
	@NullSource
	@Tag("UnitTest")
	void testNullNetName(String name) throws ParseException {
		Exception exception = null;
		SocialNetwork socialNetwork = new SocialNetwork();
		socialNetwork.setUrl("gg/gg");
		try {
			socialNetwork.setName(name);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			SocialNetwork sc = new SocialNetwork("url", name, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1254"),
					"thumb", true);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}

	@ParameterizedTest
	@EmptySource
	@Tag("UnitTest")
	void testEmptyNetName(String name){
		Exception exception = null;
		SocialNetwork socialNetwork = new SocialNetwork();
		socialNetwork.setUrl("gg/gg");
		try {
			socialNetwork.setName(name);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		exception = null;
		try {
			SocialNetwork sc = new SocialNetwork("url", name, new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1254"),
					"thumb", true);
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
	}

}