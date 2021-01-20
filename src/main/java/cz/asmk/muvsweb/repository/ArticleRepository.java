package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
