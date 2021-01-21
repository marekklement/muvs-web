package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Article;
import cz.asmk.muvsweb.repository.ArticleRepository;
import cz.asmk.muvsweb.service.api.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	private static Logger LOG = Logger.getLogger(ArticleServiceImpl.class.getName());
	//
	private final static int MINIMAL_TEXT_LENGTH = 2;
	private final static int MINIMAL_TITLE_LENGTH = 2;
	private final static int MAXIMAL_TEXT_LENGTH = 20000;
	private final static int MAXIMAL_TITLE_LENGTH = 50;
	//
	private final static String ARTICLE_NOT_FOUND = "Article not found in database!";
	private final static String ARTICLE_TITLE_NOT_FOUND = "English or Czech title should be set. Both are null currently!";
	private final static String ARTICLE_TITLE_WRONG_LENGTH = "Title has wrong length!";
	private final static String ARTICLE_TEXT_WRONG_LENGTH = "Text has wrong length!";


	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public Article get(Long id) {
		return articleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Article> findAll() {
		return IterableUtils.toList(articleRepository.findAll());
	}

	@Override
	public Article delete(Long id) {
		Article article = this.get(id);
		articleRepository.delete(article);
		return article;
	}

	@Override
	public Article update(Article entity) {
		Article article = this.get(entity.getId());
		if(Objects.isNull(article)) throw new IllegalArgumentException(ARTICLE_NOT_FOUND);
		checkArticleLength(entity);
		article = entity;
		return articleRepository.save(article);
	}

	@Override
	public Article save(Article entity) {
		if(checkIfTitleNotSet(entity)){
			throw new IllegalArgumentException(ARTICLE_TITLE_NOT_FOUND);
		}
		checkArticleLength(entity);
		return articleRepository.save(entity);
	}

	private boolean checkIfTitleNotSet(Article article){
		return Objects.nonNull(article.getTitleEn()) && Objects.nonNull(article.getTitleCz());
	}

	private void checkArticleLength(Article article){
		boolean titleEn = checkTextLength(article.getTitleEn(), MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleCz = checkTextLength(article.getTitleCz(), MINIMAL_TITLE_LENGTH, MAXIMAL_TITLE_LENGTH);
		boolean titleOK = titleCz || titleEn;
		if(!titleOK){
			throw new IllegalArgumentException(ARTICLE_TITLE_WRONG_LENGTH);
		}
		boolean textEn = checkTextLength(article.getTextEn(), MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textCz = checkTextLength(article.getTextCz(), MINIMAL_TEXT_LENGTH, MAXIMAL_TEXT_LENGTH);
		boolean textOK = textCz || textEn;
		if(!textOK){
			throw new IllegalArgumentException(ARTICLE_TEXT_WRONG_LENGTH);
		}
	}

	private boolean checkTextLength(String text, int min, int max){
		if(Objects.isNull(text)){
			LOG.info("Text not set!");
			return false;
		}
		return text.length() < max && text.length() >= min;
	}
}
