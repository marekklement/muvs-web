package cz.asmk.muvsweb.service.api;

import cz.asmk.muvsweb.entity.MUVSUser;

public interface MUVSUserService extends EntityService<MUVSUser> {

	MUVSUser findByUsername(String username);
}
