package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
}
