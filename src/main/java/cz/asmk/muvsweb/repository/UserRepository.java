package cz.asmk.muvsweb.repository;

import org.springframework.data.repository.CrudRepository;

import cz.asmk.muvsweb.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
