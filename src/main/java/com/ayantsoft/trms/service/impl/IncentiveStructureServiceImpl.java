package com.ayantsoft.trms.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ayantsoft.trms.dao.IncentiveStructureDao;
import com.ayantsoft.trms.pojo.IncentiveStructure;
import com.ayantsoft.trms.service.IncentiveStructureService;

@Service
public class IncentiveStructureServiceImpl implements Serializable,IncentiveStructureService {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4846798745622769834L;
	
	@Autowired
	private IncentiveStructureDao incentiveStructureDao;

	@Override
	public List<IncentiveStructure> findIncentiveList(String typeFor) {
		return incentiveStructureDao.findIncentiveList(typeFor);
	}

	@Override
	public double getIncentive(double amount, String typeFor) {
		return incentiveStructureDao.getIncentive(amount,typeFor);
	}

}
