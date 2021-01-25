package cz.asmk.muvsweb.util;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import cz.asmk.muvsweb.entity.Article;

public class LengthUtil {

	private static Logger LOG = Logger.getLogger(LengthUtil.class.getName());
	//
	public final static int MINIMAL_TEXT_LENGTH = 2;
	public final static int MINIMAL_TITLE_LENGTH = 2;
	public final static int MINIMAL_PEREX_LENGTH = 2;
	public final static int MINIMAL_FILENAME_LENGTH = 5;
	public final static int MAXIMAL_TEXT_LENGTH = 60;
	public final static int MAXIMAL_TITLE_LENGTH = 50;
	public final static int MAXIMAL_PEREX_LENGTH = 55;
	public final static int MAXIMAL_FILENAME_LENGTH = 30;
	//
	public final static String ARTICLE_TITLE_WRONG_LENGTH = "Given text has wrong length!";
	public final static String ARTICLE_PEREX_WRONG_LENGTH = "Given perex has wrong length!";
	public final static String ARTICLE_TEXT_WRONG_LENGTH = "Text has wrong length!";
	private final static String ARTICLE_TITLE_NOT_FOUND = "English or Czech title should be set. Both are null currently!";

	public static void checkIfArticleTitleSet(Article article){
		checkIfSomeLocationSet(article.getTitleCz(), article.getTitleEn());
	}

	public static void checkIfSomeLocationSet(String titleCz, String titleEn){
		boolean b = StringUtils.isNotBlank(titleCz) || StringUtils.isNotBlank(titleEn);
		if(!b) throw new IllegalArgumentException(ARTICLE_TITLE_NOT_FOUND);
	}

	public static void checkArticleLength(Article article){
		boolean titleEn = checkLength(article.getTitleEn(), MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleCz = checkLength(article.getTitleCz(), MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleOK = titleCz || titleEn;
		if(!titleOK){
			throw new IllegalArgumentException(ARTICLE_TITLE_WRONG_LENGTH);
		}
		boolean textEn = checkLength(article.getTextEn(), MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textCz = checkLength(article.getTextCz(), MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textOK = textCz || textEn;
		if(!textOK){
			throw new IllegalArgumentException(ARTICLE_TEXT_WRONG_LENGTH);
		}
	}

	public static void checkTitleLength(String titleCzk, String titleEng){
		boolean titleEn = checkLength(titleEng, MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleCz = checkLength(titleCzk, MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleOK = titleCz && titleEn;
		if(!titleOK){
			throw new IllegalArgumentException(ARTICLE_TITLE_WRONG_LENGTH);
		}
	}

	public static void checkPerexLength(String perexCz, String perexEn){
		boolean perexCzk = checkLength(perexCz, MINIMAL_PEREX_LENGTH, MAXIMAL_PEREX_LENGTH);
		boolean perexEng = checkLength(perexEn, MINIMAL_PEREX_LENGTH, MAXIMAL_PEREX_LENGTH);
		boolean perexOk = perexCzk && perexEng;
		if(!perexOk){
			throw new IllegalArgumentException(ARTICLE_PEREX_WRONG_LENGTH);
		}
	}

	public static void checkTextLength(String textCzk, String textEng){
		boolean textEn = checkLength(textEng, MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textCz = checkLength(textCzk, MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textOK = textCz && textEn;
		if(!textOK){
			throw new IllegalArgumentException(ARTICLE_TEXT_WRONG_LENGTH);
		}
	}

	public static boolean checkLength(String text, int min, int max){
		if(!StringUtils.isNotBlank(text)){
			LOG.info("Text not set!");
			return true;
		}
		return text.length() < max && text.length() >= min;
	}
}
