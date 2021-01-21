package cz.asmk.muvsweb.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.asmk.muvsweb.entity.Article;
import cz.asmk.muvsweb.service.api.ArticleService;

@Controller
public class ArticleController {

	private static Logger logger = Logger.getLogger(ArticleController.class.getName());

	@Autowired
	private ArticleService articleService;

	@PostMapping("/articles/{id}")
	public String update(@PathVariable(value = "id") long articleId, @RequestParam String title, @RequestParam String titleEn,
						 @RequestParam String perex, @RequestParam String perexEn, @RequestParam String text, @RequestParam String textEn) {

		Article article = articleService.get(articleId);


		article.setTitleCz(title);
		article.setPerexCz(perex);
		article.setTextCz(text);

		article.setTitleEn(titleEn);
		article.setPerexEn(perexEn);
		article.setTextEn(textEn);

		articleService.save(article);
		logger.info("Article saved successfully!");
		return "admin/articles";
	}
}
