package com.repo;

import com.bean.SysUserPO;
import com.commons.BaseRepository;
public interface SysUserRepository extends BaseRepository<SysUserPO, Long> {

	public SysUserPO findByUserNameAndPassWord(String userName,String passWord);
	
}
