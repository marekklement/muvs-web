package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Article;
import cz.asmk.muvsweb.repository.ArticleRepository;
import cz.asmk.muvsweb.service.api.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

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
		if(Objects.isNull(article)) throw new IllegalArgumentException("Article not found in database!");
		return articleRepository.save(article);
	}

	@Override
	public Article save(Article entity) {
		return articleRepository.save(entity);
	}
}
