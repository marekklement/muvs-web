package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.SocialNetwork;
import cz.asmk.muvsweb.repository.SocialNetworkRepository;
import cz.asmk.muvsweb.service.api.SocialNetworkService;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {

	@Autowired
	private SocialNetworkRepository socialNetworkRepository;

	@Override
	public SocialNetwork get(Long id) {
		return socialNetworkRepository.findById(id).orElse(null);
	}

	@Override
	public List<SocialNetwork> findAll() {
		return IterableUtils.toList(socialNetworkRepository.findAll());
	}

	@Override
	public SocialNetwork delete(Long id) {
		SocialNetwork socialNetwork = this.get(id);
		socialNetworkRepository.delete(socialNetwork);
		return socialNetwork;
	}

	@Override
	public SocialNetwork update(SocialNetwork entity) {
		SocialNetwork socialNetwork = this.get(entity.getId());
		if(Objects.isNull(socialNetwork)) throw new IllegalArgumentException("SocialNetwork not found in database!");
		socialNetwork = entity;
		return socialNetworkRepository.save(socialNetwork);
	}

	@Override
	public SocialNetwork save(SocialNetwork entity) {
		return socialNetworkRepository.save(entity);
	}
}
