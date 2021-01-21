package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cz.asmk.muvsweb.entity.Article;
import cz.asmk.muvsweb.service.api.ArticleService;

@SpringBootTest
class ArticleServiceImplTest {

	@Autowired
	private ArticleService articleService;

	@Test
	public void testNewArticle(){
		Article article = new Article();
		article.setTitleCz("Title");
		article.setTextCz("Test test");
		Article returned = articleService.save(article);
		// get from service
		Article finalArticle = articleService.get(returned.getId());
		assertEquals(article.getTextCz(), finalArticle.getTextCz());
		// find all - should be just one article
		List<Article> all = articleService.findAll();
		assertEquals(all.size(),1);
	}

}