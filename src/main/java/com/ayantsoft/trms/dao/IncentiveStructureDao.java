package com.ayantsoft.trms.dao;
import java.util.List;

import com.ayantsoft.trms.pojo.IncentiveStructure;

public interface IncentiveStructureDao {
	
	public List<IncentiveStructure> findIncentiveList(String typeFor);
	public double getIncentive(double amount,String typeFor);

}
