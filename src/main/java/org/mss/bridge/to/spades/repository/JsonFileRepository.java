package org.mss.bridge.to.spades.repository;

import java.io.File;

import java.io.FileWriter;
import java.util.List;

import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




public interface JsonFileRepository extends JpaRepository <JsonFile , Long>{


	void save (File targetFile);
	
	 @Query(value="SELECT * FROM json_file WHERE name LIKE CONCAT('%',:name,'%')" , nativeQuery = true)
	    List<JsonFile> getScenarioByName(@Param("name")String name);

	 @Query(value="SELECT * FROM json_file WHERE name LIKE CONCAT('%',:name,'%')" , nativeQuery = true)
	   JsonFile getScenarioData(@Param("name")String name);
}
