package com.ayantsoft.trms.dao;

import com.ayantsoft.trms.exception.SequenceException;

public interface commonDao {
	
	String getNextSequenceId(String key) throws SequenceException;

}
