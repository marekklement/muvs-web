package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import cz.asmk.muvsweb.util.LengthUtil;

class CategoryTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/Category-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewCategory(String perexCz, String perexEn, String titleCz, String titleEn, String result){
		boolean res = Boolean.valueOf(result);
		Exception exception = null;
		Category category = null;
		try {
			category = new Category(titleCz, perexCz, titleEn, perexEn, null);
			category.setId(1);
		} catch (Exception e){
			exception = e;
		}
		if(res){
			assertNotNull(category);
			assertNull(exception);
			Category cat = new Category();
			Exception ex = null;
			try {
				cat.setTitleCz(titleCz);
				cat.setTitleEn(titleEn);
				cat.setPerexCz(perexCz);
				cat.setPerexEn(perexEn);
				cat.setArticles(null);
				cat.setId(1);
			} catch (Exception e){
				ex = e;
			}
			assertNull(ex);
			assertEquals(category, cat);
		} else {
			assertNull(category);
			assertNotNull(exception);
		}
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/Category-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testWrongShortTitle(){
		Exception exception = null;
		Category category = new Category();
		try {
			category.setTitleCz("A");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		assertEquals(exception.getMessage(), LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		exception = null;
		try {
			category.setTitleEn(
					"Longgggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
		} catch (Exception e){
			exception = e;
		}
		assertNotNull(exception);
		assertEquals(exception.getMessage(), LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
	}

}