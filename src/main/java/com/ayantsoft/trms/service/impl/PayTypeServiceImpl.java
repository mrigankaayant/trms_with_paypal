package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.PayTypeDao;
import com.ayantsoft.trms.pojo.PayType;
import com.ayantsoft.trms.service.PayTypeService;

@Service
public class PayTypeServiceImpl implements Serializable,PayTypeService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -7845327921974936826L;
	
	@Autowired
	private PayTypeDao payTypeDao;

	@Override
	public List<PayType> payTypeList() {
		return payTypeDao.list();
	}

}
