package org.mss.bridge.to.spades.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.mss.bridge.to.spades.domain.JsonFile;
import org.mss.bridge.to.spades.repository.JsonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;



@Service
public class JsonFileService {

	@Autowired
	JsonFileRepository jsonFileRepository;


public String excel2Json(MultipartFile data, String fileName) {
String message="";
try {

XSSFWorkbook workBook = new XSSFWorkbook(data.getInputStream());

if(!isSheetEmpty(workBook)) {

//if(isDataCoherent(workBook)) {

List<JSONObject> dataList = new ArrayList<>();




//itérer sur les sheets d'un fichier excel
Iterator<Sheet> sheetIterator;
sheetIterator = workBook.sheetIterator();
while(sheetIterator.hasNext()){
dataList= new ArrayList<>();
XSSFSheet workSheet = (XSSFSheet) sheetIterator.next();
System.out.println(workSheet.getSheetName());
// int sheetIndex=workSheet.getWorkbook().getSheetIndex(workSheet);
//    if(sheetIndex==0) {
//     //traitement sur first sheet
//
//
// for(int i=1;i<=workSheet.getLastRowNum();i++) {
//    
// XSSFRow row = workSheet.getRow(i);
// if(isRowEmpty(row)==false) {
// JSONObject rowJsonHeader = new JSONObject();
// XSSFRow titleFirstSheetRow = workSheet.getRow(0);
// String firstSheetTitle= titleFirstSheetRow.getCell(0).toString();
// rowJsonHeader.put(" first sheet title", firstSheetTitle);
// JSONObject rowJsonObject = new JSONObject();
//
// for(int j=0; j<row.getLastCellNum();j++) {
// DataFormatter formatter = new DataFormatter();
// Cell cell = row.getCell(j);
// //voir si les cellule ne sont pas vides
// if(!formatter.formatCellValue(cell).isEmpty()){
// String columnName = row.getCell(0).toString();
// String columnValue = row.getCell(1).toString();
// rowJsonObject.put(columnName, columnValue);
//
// }
//
//
// }
//
// dataList.add(rowJsonObject);
//
// }
//
// }
// System.out.println(dataList);
//    }
//    else {
//     //definir le titre du sheeet
//     JSONObject rowJsonHeader = new JSONObject();
// XSSFRow titleSheet = workSheet.getRow(0);
// String sheetTitle= titleSheet.getCell(0).toString();
// rowJsonHeader.put("sheet title", sheetTitle);
//
// //definir id, name, version du Process
// XSSFRow scenarioRow = workSheet.getRow(1);
// //id
// String scId= scenarioRow.getCell(0).toString();
// rowJsonHeader.put("Process code", scId);
// //name
// String scName= scenarioRow.getCell(1).toString();
// rowJsonHeader.put("Process Name", scName);
// //version
// String scVer= scenarioRow.getCell(2).toString();
// rowJsonHeader.put("Process Version", scVer);
// dataList.add(rowJsonHeader);


   //defenir l'entete du fichier
   XSSFRow header = workSheet.getRow(2);
   
 
    for(int i=3;i<=workSheet.getLastRowNum();i++) {
   
XSSFRow row = workSheet.getRow(i);
if(isRowEmpty(row)==false) {

JSONObject rowJsonObject = new JSONObject();

for(int j=0; j<row.getLastCellNum();j++) {
DataFormatter formatter = new DataFormatter();
Cell cell = row.getCell(j);
//voir si les cellule ne sont pas vides
if(!formatter.formatCellValue(cell).isEmpty()){
String columnName = header.getCell(j).toString();
String columnValue = row.getCell(j).toString();
rowJsonObject.put(columnName, columnValue);

}
else {
String columnName = header.getCell(j).toString();
String columnValue = null;
rowJsonObject.put(columnName, columnValue);
}

}

dataList.add(rowJsonObject);
}

}
    String jsonFileName=fileName+"_"+workSheet.getSheetName();
    storeJsonFile(jsonFileName,dataList);
System.out.println(dataList);
message="Success!!";
   }
   //}

//}

//else {message="Your data is not coherent!!";}

}

else {message="Your document has an empty sheet!!";}


} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return message;
}

	// fonction verifier un sheet est vide ou non

	public boolean isSheetEmpty(XSSFWorkbook workBook) {
		boolean isEmpty = false;
		Iterator<Sheet> sheetIterator;
		sheetIterator = workBook.sheetIterator();
		while (sheetIterator.hasNext()) {
			XSSFSheet workSheet = (XSSFSheet) sheetIterator.next();

			// verifier si sheet est vide ou nn
			if ((workSheet.getPhysicalNumberOfRows() <= 1) || (workSheet.getPhysicalNumberOfRows() <= 3)) {
				isEmpty = true;
				break;
			}
		}
		return isEmpty;
	}

	// function verifier la cohérence des données

	public boolean isDataCoherent(XSSFWorkbook workBook) {
		boolean isCoherent = false;
		List<String> namesList = new ArrayList<>();
		List<String> allSheetList = new ArrayList<>();
		Iterator<Sheet> sheetIterator;
		sheetIterator = workBook.sheetIterator();
		while (sheetIterator.hasNext()) {
			XSSFSheet workSheet = (XSSFSheet) sheetIterator.next();
			XSSFRow header = workSheet.getRow(2);
			for (int i = 3; i <= workSheet.getLastRowNum(); i++) {

				XSSFRow row = workSheet.getRow(i);
				if (isRowEmpty(row) == false) {

					for (int j = 0; j < row.getLastCellNum(); j++) {
						DataFormatter formatter = new DataFormatter();
						Cell cell = row.getCell(j);
						// voir si les cellule ne sont pas vides
						if (!formatter.formatCellValue(cell).isEmpty()) {

							String columnValue = row.getCell(j).toString();
							if (!header.getCell(j).toString().equalsIgnoreCase("NAME")) {
								allSheetList.add(columnValue);
							}
							if (header.getCell(j).toString().equalsIgnoreCase("NAME")) {
								namesList.add(columnValue);
							}

						}

					}

				}

			}

		}
		System.out.println("all list!!" + allSheetList);
		System.out.println("name list!!" + namesList);

		counter: for (int i = 0; i < namesList.size(); i++) {
			// long list2 = allSheetList.stream().filter(x -> x.contains(namesList.get(2)));
			int nameCount = 0;
			boolean exist = false;
			for (int j = 0; j < allSheetList.size(); j++) {
				if (namesList.get(i) == allSheetList.get(j) && nameCount < 2) {
					nameCount++;
					exist = true;
					isCoherent = true;
				}

			}
			if (exist == false) {
				isCoherent = false;
				break counter;
			}
		}
		return isCoherent;
	}

	// fonction pour verifier si une ligne est vide ou nn
	public boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();
		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}

//save data to database
	public JsonFile storeJsonFile(String name, List<JSONObject> file) throws IOException {
		JsonFile result = null;

		JsonFile jsonFile = new JsonFile(name, file);
		result = jsonFileRepository.save(jsonFile);
		System.out.println("datalist verifyyy!!" + jsonFile);

		return result;
	}

	// get scenario from data base

	public List<JsonFile> getScenario(String name) {

		List<JsonFile> scenario = jsonFileRepository.getScenarioByName(name);

		return scenario;
	}
	
	//get data of scenario from data base

	public JsonFile getScenarioData(String name) {
	String dataFile = name+".xlsx_Actors";
	JsonFile scenario = jsonFileRepository.getScenarioData(dataFile);
	           

	       return scenario;
	   }
	public List<String> processScenarioData(JsonFile jsonFile) {

	List<JSONObject> list = new ArrayList<JSONObject>();
	list = jsonFile.getData();
	List<String> list2 = new ArrayList<String>();

	for(int i = 0 ; i < list.size() ; i++){
	JSONObject obj = new JSONObject(list.get(i));
	String attName = String.valueOf(obj.get("NAME"));
	list2.add(attName);

	}
	return list2;
	     
	   }
}