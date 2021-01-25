package cz.asmk.muvsweb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUtil {

	public static void checkPhoneNumber(String phone){
		Pattern pattern = Pattern.compile("^\\+?[\\d ]+");
		Matcher matcher = pattern.matcher(phone);
		if(!matcher.matches()) throw new IllegalArgumentException("Phone number have wrong syntax!");
	}

	public static void checkEmail(String email){
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = java.util.regex.Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		if(!m.matches()) throw new IllegalArgumentException("Email address has wrong syntax!");
	}

	public static void validateFirstName(String firstName) {
		boolean matches = firstName.matches("[A-Z][a-z]*");
		if(!matches){
			throw new IllegalArgumentException("First name has wrong regex!");
		}
	}

	public static void validateLastName(String lastName) {
		boolean matches = lastName.matches("\\b([A-ZÀ-ÿ][-,a-z. ']+[ ]*)+");
		if(!matches){
			throw new IllegalArgumentException("Last name has wrong regex!");
		}
	}
}
