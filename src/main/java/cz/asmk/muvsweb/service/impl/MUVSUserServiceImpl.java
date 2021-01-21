package cz.asmk.muvsweb.service.impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.asmk.muvsweb.entity.MUVSUser;
import cz.asmk.muvsweb.repository.MUVSUserRepository;
import cz.asmk.muvsweb.service.api.MUVSUserService;

@Service
public class MUVSUserServiceImpl implements MUVSUserService {

	@Autowired
	private MUVSUserRepository muvsUserRepository;

	@Override
	public MUVSUser get(Long id) {
		return muvsUserRepository.findById(id).orElse(null);
	}

	@Override
	public List<MUVSUser> findAll() {
		return IterableUtils.toList(muvsUserRepository.findAll());
	}

	@Override
	public MUVSUser delete(Long id) {
		MUVSUser muvsUser = this.get(id);
		muvsUserRepository.delete(muvsUser);
		return muvsUser;
	}

	@Override
	public MUVSUser update(MUVSUser muvsUser) {
		MUVSUser foundUser = this.get(muvsUser.getId());
		if(Objects.isNull(foundUser)) throw new IllegalArgumentException("User not found in database!");
		foundUser = muvsUser;
		return muvsUserRepository.save(foundUser);
	}

	@Override
	public MUVSUser save(MUVSUser muvsUser) {
		return muvsUserRepository.save(muvsUser);
	}

	@Override
	public MUVSUser findByUsername(String username) {
		return muvsUserRepository.findByUsername(username);
	}
}
