package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {
}
