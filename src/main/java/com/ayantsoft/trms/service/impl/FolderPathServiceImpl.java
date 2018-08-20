package com.ayantsoft.trms.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.FolderPathDao;
import com.ayantsoft.trms.service.FolderPathService;

@Service
public class FolderPathServiceImpl implements Serializable,FolderPathService{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4122322059380394416L;
	
	
	@Autowired
	private FolderPathDao folderPathDao;
	
	
	@Override
	public String getFolderPath(String pathFor) {
		return folderPathDao.getFolderPath(pathFor);
	}
	
	

}
