package cz.asmk.muvsweb.service.api;

import java.util.List;

public interface EntityService<T> {

	T get(Long id);

	List<T> findAll();

	T delete(Long id);

	T update(T entity);

	T save(T entity);
}
