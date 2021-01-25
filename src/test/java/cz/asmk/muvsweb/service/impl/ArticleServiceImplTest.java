package cz.asmk.muvsweb.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Tag;
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
	@Tag("IntegrationTest")
	void testNewArticle(){
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
		// delete from database
		articleService.delete(finalArticle.getId());
	}

	@Test
	@Tag("IntegrationTest")
	void testUpdateArticle(){
		Article article = new Article();
		article.setTitleCz("Title");
		article.setTextCz("Test test");
		Article returned = articleService.save(article);
		// get from service
		Article finalArticle = articleService.get(returned.getId());
		assertEquals(article.getTextCz(), finalArticle.getTextCz());
		// find all - should be just one article
		finalArticle.setTitleEn("Test title");
		Article update = articleService.update(finalArticle);
		assertNotNull(update.getTitleEn());
		assertEquals(update.getTitleEn(), finalArticle.getTitleEn());
		Article test = articleService.get(update.getId());
		assertEquals(test.getTitleEn(), finalArticle.getTitleEn());
		//
		articleService.delete(finalArticle.getId());
	}

	@Test
	@Tag("IntegrationTest")
	void testGetArticle(){
		Article article = new Article();
		article.setTitleCz("Title");
		article.setTextCz("Test test");
		Article returned = articleService.save(article);
		// get from service
		Article finalArticle = articleService.get(returned.getId());
		assertEquals(article.getTextCz(), finalArticle.getTextCz());
		//
		articleService.delete(finalArticle.getId());
	}

	@Test
	@Tag("IntegrationTest")
	void deleteArticle(){
		Article article = new Article();
		article.setTitleCz("Title");
		article.setTextCz("Test test");
		Article returnedArticle = articleService.save(article);
		//
		Article article2 = new Article();
		article2.setTitleCz("Title2");
		article2.setTextCz("Test test2");
		Article returnedArticle2 = articleService.save(article2);
		//
		List<Article> all = articleService.findAll();
		assertEquals(all.size(),2);
		//
		articleService.delete(returnedArticle.getId());
		all = articleService.findAll();
		assertEquals(all.size(),1);
		assertEquals(returnedArticle2.getId(), all.get(0).getId());
		articleService.delete(returnedArticle2.getId());
		all = articleService.findAll();
		assertEquals(all.size(),0);
	}

}