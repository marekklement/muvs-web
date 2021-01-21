package cz.asmk.muvsweb.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.asmk.muvsweb.entity.MUVSUser;

public interface MUVSUserRepository extends CrudRepository<MUVSUser, Long> {

	@Query( "select u from MUVSUser u where u.username = :username" )
	MUVSUser findByUsername(@Param("username") String username);

}
