package com.ayantsoft.trms.service.impl;

import java.io.File;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.ayantsoft.trms.dao.FileUploadDao;
import com.ayantsoft.trms.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements Serializable,FileUploadService {

	/**
	 *serialVersionUID 
	 */
	private static final long serialVersionUID = 5899729880620437125L;
	
	@Autowired
	private FileUploadDao fileUploadDao;

	@Override
	public void uploadEnrollmentForm(String fileName, File file, String contentType) {
		fileUploadDao.uploadEnrollmentForm(fileName, file, contentType);
	}

	/*@Override
	public String downLoadEnrollmentFrom( String filePath,String fileName,String candidateName,String candidateId) {
		return fileUploadDao.downLoadEnrollmentFrom(fileName, filePath,candidateName,candidateId);
	}*/
	
	public InputStreamResource downLoadEnrollmentFrom( String fileName) {
		return fileUploadDao.downLoadEnrollmentFrom(fileName);
	}

}
