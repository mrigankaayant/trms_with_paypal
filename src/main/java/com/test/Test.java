package com.test;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class Test {

	public static void main(String[] args) {
		
		try {

			SimpleMongoDbFactory simpleMongoDbFactory =	new SimpleMongoDbFactory(new MongoClient("localhost",27017),"ayant_trmsv2");
			DB db = simpleMongoDbFactory.getDb();
			//DBCollection collection = db.getCollection("document");
			
			// STORE

			/*String newFileName = "somnath12";
			File imageFile = new File("/home/mriganka/Desktop/upload_file_testing/upload/somnath12.mp4");
			GridFS gfsPhoto = new GridFS(db,"resume_details");
			GridFSInputFile gfsFile = gfsPhoto.createFile(imageFile);
			gfsFile.setFilename(newFileName);
			gfsFile.setContentType("application/mp4");
			gfsFile.save();*/
			
			
			//FETCH FILE FROM DATABASE

			/* DBCursor cursor = gfsPhoto.getFileList();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}*/
			
			GridFS gfsPhoto = new GridFS(db,"upload_enrollment_form");
			GridFSDBFile imageForOutput = gfsPhoto.findOne("Mriganka Koley_101");
			
			System.out.println(imageForOutput.getContentType());
			System.out.println(imageForOutput.getFilename());
		
			
			imageForOutput.writeTo("/home/mriganka/Desktop/upload_file_testing/create/"+"Mriganka Koley_101"+".pdf");

			// remove the image file from mongoDB
			//gfsPhoto.remove(gfsPhoto.findOne(newFileName));

			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
