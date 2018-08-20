package com.ayantsoft.trms.dao.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.ayantsoft.trms.dao.FolderPathDao;
import com.ayantsoft.trms.pojo.Folderpath;

@Repository
public class FolderPathDaoImpl implements Serializable,FolderPathDao {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7690804998383580729L;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String getFolderPath(String pathFor) {
		String path = null;
		try{
			Query query = new Query();
			query.addCriteria(Criteria.where("pathFor").is(pathFor));
			Folderpath folderpath = mongoTemplate.findOne(query,Folderpath.class,"folder_path");
			if(folderpath != null){
				path = folderpath.getPath();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return path;
	}

}
