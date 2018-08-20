package com.ayantsoft.trms.service;

import com.ayantsoft.trms.pojo.UserMst;

public interface UserMstService {
	
	public UserMst getUserMstByUsername(String userName);
	public UserMst createUser(UserMst userMst);
	public void updateUserMast(UserMst userMst);
}
