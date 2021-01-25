package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.Link;
import cz.asmk.muvsweb.repository.LinkRepository;
import cz.asmk.muvsweb.service.api.LinkService;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private LinkRepository linkRepository;

	@Override
	public Link get(Long id) {
		return linkRepository.findById(id).orElse(null);
	}

	@Override
	public List<Link> findAll() {
		return IterableUtils.toList(linkRepository.findAll());
	}

	@Override
	public Link delete(Long id) {
		Link link = this.get(id);
		linkRepository.delete(link);
		return link;
	}

	@Override
	public Link update(Link entity) {
		Link link = this.get(entity.getId());
		if(Objects.isNull(link)) throw new IllegalArgumentException("Link not found in database!");
		link = entity;
		return linkRepository.save(link);
	}

	@Override
	public Link save(Link entity) {
		return linkRepository.save(entity);
	}
}
