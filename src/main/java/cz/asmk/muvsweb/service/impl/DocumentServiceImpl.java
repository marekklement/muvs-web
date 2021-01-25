package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Document;
import cz.asmk.muvsweb.repository.DocumentRepository;
import cz.asmk.muvsweb.service.api.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public Document get(Long id) {
		return documentRepository.findById(id).orElse(null);
	}

	@Override
	public List<Document> findAll() {
		return IterableUtils.toList(documentRepository.findAll());
	}

	@Override
	public Document delete(Long id) {
		Document document = this.get(id);
		documentRepository.delete(document);
		return document;
	}

	@Override
	public Document update(Document entity) {
		Document document = this.get(entity.getId());
		if(Objects.isNull(document)) throw new IllegalArgumentException("Document not found in database!");
		document = entity;
		return documentRepository.save(document);
	}

	@Override
	public Document save(Document entity) {
		return documentRepository.save(entity);
	}
}
