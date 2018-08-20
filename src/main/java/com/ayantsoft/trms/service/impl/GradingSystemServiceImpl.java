package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.GradingSystemDao;
import com.ayantsoft.trms.pojo.GradingSystem;
import com.ayantsoft.trms.service.GradingSystemService;

@Service
public class GradingSystemServiceImpl implements Serializable,GradingSystemService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = -727298381926947471L;
	
	@Autowired
	private GradingSystemDao gradingSystemDao;

	@Override
	public List<GradingSystem> list() {
		return gradingSystemDao.list();
	}

}
