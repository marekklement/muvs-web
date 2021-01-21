package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Category;
import cz.asmk.muvsweb.repository.CategoryRepository;
import cz.asmk.muvsweb.service.api.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category get(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public List<Category> findAll() {
		return IterableUtils.toList(categoryRepository.findAll());
	}

	@Override
	public Category delete(Long id) {
		Category category = this.get(id);
		categoryRepository.delete(category);
		return category;
	}

	@Override
	public Category update(Category entity) {
		Category category = this.get(entity.getId());
		if(Objects.isNull(category)) throw new IllegalArgumentException("User not found in database!");
		category = entity;
		return categoryRepository.save(category);
	}

	@Override
	public Category save(Category entity) {
		return categoryRepository.save(entity);
	}
}
