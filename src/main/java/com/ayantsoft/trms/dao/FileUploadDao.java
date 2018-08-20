package com.ayantsoft.trms.dao;

import java.io.File;

import org.springframework.core.io.InputStreamResource;

public interface FileUploadDao {
	public void uploadEnrollmentForm(String fileName,File file,String contentType);
	//public String downLoadEnrollmentFrom(String fileName,String filePath,String candidateName,String candidateId);
	public InputStreamResource downLoadEnrollmentFrom(String fileName);
}
