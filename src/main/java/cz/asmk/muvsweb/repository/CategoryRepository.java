package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
