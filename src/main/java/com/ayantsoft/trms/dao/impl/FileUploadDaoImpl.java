package com.ayantsoft.trms.dao.impl;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Repository;

import com.ayantsoft.trms.dao.FileUploadDao;
import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

@Repository
public class FileUploadDaoImpl implements Serializable,FileUploadDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8880583962583317787L;


	@Autowired
	private MongoDbFactory mongoDbFactory;
	

	@Override
	public void uploadEnrollmentForm(String fileName, File file, String contentType) {
		try{
			System.out.println("In Dao File Name"+fileName);
			DB db = mongoDbFactory.getDb();
			GridFS gridFS = new GridFS(db,"upload_enrollment_form");
			GridFSDBFile gridFSDBFile = gridFS.findOne(fileName);
			
			if(gridFSDBFile != null){
			if(gridFSDBFile.getChunkSize() > 0){
				List<GridFSDBFile> fileList = gridFS.find(fileName);
				 for (GridFSDBFile f : fileList)
				    {
					 gridFS.remove(f.getFilename());
				        
				    }
			}
			}
			GridFSInputFile gfsFile = gridFS.createFile(file);
			gfsFile.setFilename(fileName);
			gfsFile.setContentType(contentType);
			gfsFile.save();

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/*@Override
	public String downLoadEnrollmentFrom(String fileName,String filePath,String candidateName,String candidateId) {    // filepath = /home/mriganka/projects/angular_workspace/trms_candidate_CLIENT/src/app/asset/enrollment_form/
		String extension = null;
		try{
			
			DB db = mongoDbFactory.getDb();
			GridFS gfsPhoto = new GridFS(db,"upload_enrollment_form");
			GridFSDBFile gridFSDBFile = gfsPhoto.findOne(fileName);
			if("application/pdf".equals(gridFSDBFile.getContentType())){
				extension = ".pdf";
				filePath = filePath+gridFSDBFile.getFilename()+".pdf";
				System.out.println("FilePath"+filePath);
			}else if("image/png".equals(gridFSDBFile.getContentType())){
				extension = ".png";
				filePath = filePath+gridFSDBFile.getFilename()+".png"; 	
			}else if("image/jpeg".equals(gridFSDBFile.getContentType())){
				extension = ".jpeg";
				filePath = filePath+gridFSDBFile.getFilename()+".jpeg"; 	
			}else if("image/jpg".equals(gridFSDBFile.getContentType())){
				extension = ".jpg";
				filePath = filePath+gridFSDBFile.getFilename()+".jpg"; 
			}else{
				System.out.println("FILE NOT SUPPORT"); 
			}
			
			gridFSDBFile.writeTo(filePath);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return extension;
	}*/

	/*public Response downLoadEnrollmentFrom(String id){
		Response response = null;
		try{
			System.out.println("Enter In DAo");
			 DB db = mongoDbFactory.getDb();
			 GridFS gridFS = new GridFS(db,"upload_enrollment_form");
			 GridFSDBFile gridFile = gridFS.findOne("Salwendra Shaw_106");
			 InputStream in = gridFile.getInputStream();
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 int data = in.read();
			 while (data >= 0) {
				out.write((char) data);
				data = in.read();
			 }
			 out.flush();
			 ResponseBuilder builder = Response.ok(out.toByteArray());
			 builder.header("Content-Disposition", "attachment; filename=" + gridFile.get("filename")+ gridFile.get("contentType"));
			 response = builder.build();
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	return response;
  }*/
	
	public InputStreamResource downLoadEnrollmentFrom(String fileName){
		InputStreamResource in = null;
		try{
			 DB db = mongoDbFactory.getDb();
			 GridFS gridFS = new GridFS(db,"upload_enrollment_form");
			 GridFSDBFile gridFile = gridFS.findOne(fileName);
			  in = new InputStreamResource(gridFile.getInputStream());
			 
					}catch(Exception e){
			e.printStackTrace();
		}
		return in;
	}
}
