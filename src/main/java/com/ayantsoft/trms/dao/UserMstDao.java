package com.ayantsoft.trms.dao;

import com.ayantsoft.trms.pojo.UserMst;

public interface UserMstDao {
	
     public UserMst getUserMstByUsername(String userName);
     public UserMst createUser(UserMst userMst);
     public void updateUserMast(UserMst userMst);

}
