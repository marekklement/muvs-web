package cz.asmk.muvsweb.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import cz.asmk.muvsweb.util.LengthUtil;

class ArticleTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/csv-test/Article-in.csv", numLinesToSkip = 1)
	@Tag("UnitTest")
	void testNewArticle(String titleCz, String textCz, String titleEn, String textEn, String perexCz,
							   String perexEn, String muvsUser, String result){
		boolean res = Boolean.valueOf(result);
		Exception exc = null;
		Category category = new Category();
		category.setTitleCz("Test");
		MUVSUser muvsUser1 = new MUVSUser();
		muvsUser1.setUsername(muvsUser);
		Article article = null;
		try {
			article = new Article(perexCz,titleCz, perexEn, titleEn, textCz, textEn, null, category, muvsUser1);
		} catch (Exception e){
			exc = e;
		}
		if(res){
			assertNull(exc);
			assertNotNull(article);
		} else {
			assertNotNull(exc);
			assertNull(article);
		}
	}

	@ParameterizedTest
	@CsvSource("Far tooo loooon gggggggggggggggggggggggggggggggggggggggggggggggggg")
	@Tag("UnitTest")
	void testSetTitleError(String title){
		Article article = new Article();
		Exception ex1 = null;
		try {
			article.setTitleCz(title);
		} catch (Exception e){
			ex1 = e;
		}
		assertNotNull(ex1);
		assertEquals(ex1.getMessage(), LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
		Exception ex2 = null;
		try {
			article.setTitleEn(title);
		} catch (Exception e){
			ex2 = e;
		}
		assertNotNull(ex2);
		assertEquals(ex2.getMessage(), LengthUtil.ARTICLE_TITLE_WRONG_LENGTH);
	}

	@ParameterizedTest
	@CsvSource("This title is ok")
	@Tag("UnitTest")
	void testSetTitleOk(String title){
		Article article = new Article();
		Exception ex1 = null;
		try {
			article.setTitleCz(title);
		} catch (Exception e){
			ex1 = e;
		}
		assertNull(ex1);
		assertEquals(article.getTitleCz(), title);
		Exception ex2 = null;
		try {
			article.setTitleEn(title);
		} catch (Exception e){
			ex2 = e;
		}
		assertNull(ex2);
		assertEquals(article.getTitleEn(), title);
	}

	@ParameterizedTest
	@CsvSource("T,Text ok")
	@Tag("UnitTest")
	void testSetShortText(String notOk, String ok){
		Article article = new Article();
		Exception ex1 = null;
		try {
			article.setTextCz(notOk);
		} catch (Exception e){
			ex1 = e;
		}
		assertNotNull(ex1);
		assertEquals(ex1.getMessage(), LengthUtil.ARTICLE_TEXT_WRONG_LENGTH);
		Exception ex2 = null;
		try {
			article.setTextCz(ok);
		} catch (Exception e){
			ex2 = e;
		}
		assertNull(ex2);
		assertEquals(article.getTextCz(), ok);
	}

}