package com.ayantsoft.trms.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.UserMstDao;
import com.ayantsoft.trms.pojo.UserMst;
import com.ayantsoft.trms.service.UserMstService;

@Service
public class UserMstServiceImpl implements Serializable,UserMstService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6769831286867053491L;
	
	@Autowired
	private UserMstDao userMstDao;

	@Override
	public UserMst getUserMstByUsername(String userName) {
		return userMstDao.getUserMstByUsername(userName);
	}

	@Override
	public UserMst createUser(UserMst userMst) {
		 return userMstDao.createUser(userMst);
	}

	@Override
	public void updateUserMast(UserMst userMst) {
		userMstDao.updateUserMast(userMst);
	}

}
