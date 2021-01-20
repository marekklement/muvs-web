package cz.asmk.muvsweb.service.impl;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;

import cz.asmk.muvsweb.entity.User;
import cz.asmk.muvsweb.repository.UserRepository;
import cz.asmk.muvsweb.service.api.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User get(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public List<User> findAll(Long id) {
		return IterableUtils.toList(userRepository.findAll());
	}

	@Override
	public User delete(Long id) {
		User user = this.get(id);
		userRepository.delete(user);
		return user;
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
}
