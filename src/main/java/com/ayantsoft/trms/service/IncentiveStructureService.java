package com.ayantsoft.trms.service;

import java.util.List;

import com.ayantsoft.trms.pojo.IncentiveStructure;

public interface IncentiveStructureService {
	public List<IncentiveStructure> findIncentiveList(String typeFor);
	public double getIncentive(double amount,String typeFor);
}
