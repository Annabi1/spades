package org.mss.bridge.to.spades.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.mss.bridge.to.spades.domain.Bean;
import org.mss.bridge.to.spades.domain.Beans;
import org.mss.bridge.to.spades.domain.Entry;
import org.mss.bridge.to.spades.domain.FolderConfig;
import org.mss.bridge.to.spades.domain.Map;
import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.domain.Property;
import org.mss.bridge.to.spades.domain.Ref;
import org.mss.bridge.to.spades.domain.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;



@Service
public class JsonToXmlServiceImp {

	@Autowired
	ProfilsServiceImpl profilService;
	@Autowired
	ScenarioServiceImpl scenarioService;
	@Autowired
	FolderConfigServiceImpl foldercontentService;
	
	//extra bean : userdto, wfvalidator, wfExecutor
	public List<Bean> otherBean() {
		
		List<Bean> beans = new ArrayList();
		
		//database definition bean
		Bean dbBean = new Bean();
		
		Property propdb1 = new Property();
		propdb1.setName("driverClassName");
		propdb1.setValue("org.postgresql.Driver");
		
		Property propdb2 = new Property();
		propdb2.setName("url");
		propdb2.setValue("jdbc:postgresql://localhost:5433/mmsdb");
		
		Property propdb3 = new Property();
		propdb3.setName("username");
		propdb3.setValue("emna");
		
		Property propdb4 = new Property();
		propdb4.setName("password");
		propdb4.setValue("emna");
		
		List<Property> propsDb = new ArrayList();
		propsDb.add(propdb1);
		propsDb.add(propdb2);
		propsDb.add(propdb3);
		propsDb.add(propdb4);
		
		dbBean.setId("DataSourceBean");
		dbBean.setClassB("org.springframework.jdbc.datasource.DriverManagerDataSource");
		dbBean.setProperty(propsDb);
		
		//WorkflowInstanceDAO bean definition
		Bean  wfInsBean = new Bean();
		 wfInsBean.setId("WorkflowInstanceDAO");
		 wfInsBean.setClassB("org.mss.swe.dao.WorkflowInstanceDAO");
		
		Property propWfins = new Property();
		propWfins.setName("ds");
		propWfins.setRef("DataSourceBean");
		
		Property propWfins1 = new Property();
		propWfins1.setName("logger");
		propWfins1.setValue("org.mss.swe.dao.WorkflowInstanceDAO");
		
		List<Property> propspropWfins= new ArrayList();
		propspropWfins.add(propWfins);
		propspropWfins.add(propWfins1);
		wfInsBean.setProperty(propspropWfins);
		
		//userdto bean definition
		Bean userBean = new Bean();
		userBean.setId("UserDAO");
		userBean.setClassB("org.mss.spades.dao.users.DbUserDAO");
		
		Property propUser = new Property();
		propUser.setName("logger");
		propUser.setValue("org.mss.spades.dao.users.DbUserDAO");
		
		List<Property> propsUser = new ArrayList();
		propsUser.add(propUser);
		userBean.setProperty(propsUser);
		
		//WorkflowValidator bean definition
		Bean wfValidBean = new Bean();
			Property prop1 = new Property();
			prop1.setName("widao");
			prop1.setRef("WorkflowInstanceDAO");
			
			Property prop2 = new Property();
			prop2.setName("udao");
			prop2.setRef("UserDAO");
			
			Property prop3 = new Property();
			prop3.setName("processes_nameid_list");
			
			Map map = new Map();
			
			List<Entry> entries = new ArrayList();
			
			List<Scenario> scenarioList = scenarioService.getAllScenarios();
			for(int i=0;i<scenarioList.size();i++) {
				Scenario scenario = scenarioList.get(i);
				Ref ref =new Ref();
				ref.setBean(scenario.getId_scenario());
				
				Entry entry = new Entry();
				entry.setKey(scenario.getId_scenario());
				entry.setRef(ref);
				
				entries.add(entry);
			}
		map.setEntry(entries);
		prop3.setMap(map);
		
		Property prop4 = new Property();
		prop4.setName("logger");
		prop4.setValue("org.mss.swe.processing.WorkflowValidator");
		
		List<Property> propsWfValid = new ArrayList();
		propsWfValid.add(prop1);
		propsWfValid.add(prop2);
		propsWfValid.add(prop3);
		propsWfValid.add(prop4);
		wfValidBean.setProperty(propsWfValid);
		wfValidBean.setId("WorkflowValidator");
		wfValidBean.setClassB("org.mss.swe.processing.WorkflowValidator");
		//WorkflowExecutor bean definition
		Bean wfExcBean = new Bean();

		Property prop5 = new Property();
		prop5.setName("logger");
		prop5.setValue("org.mss.swe.processing.WorkflowExecutor");
		
		List<Property> propsWexc = new ArrayList();
		propsWexc.add(prop1);
		propsWexc.add(prop2);
		propsWexc.add(prop3);
		propsWexc.add(prop5);
		
		wfExcBean.setProperty(propsWexc);
		wfExcBean.setId("WorkflowExecutor");
		wfExcBean.setClassB("org.mss.swe.processing.WorkflowExecutor");
		
		beans.add(dbBean);
		beans.add(wfInsBean);
		beans.add(userBean);
		beans.add(wfValidBean);
		beans.add(wfExcBean);
		
		
		return beans;
		
	}
	
	//scenario definition
	public List<Bean> scenarioBean() {
		List<Scenario> scenarioList = scenarioService.getAllScenarios();
		
		List<Bean> beans = new ArrayList();
		for(int i=0;i<scenarioList.size();i++) {
			
			Scenario scenario = scenarioList.get(i);
			
			Property prop1 = new Property();
			prop1.setName("process_nameid");
			prop1.setValue(scenario.getId_scenario());
			
			Property prop2 = new Property();
			prop2.setName("process_description");
			prop2.setValue(scenario.getDescription_scenario());
			
			Property prop3 = new Property();
			FolderConfig actI = foldercontentService.getActivityByIdScenarioAndType(scenario.getId_scenario(), "Initiating");
			prop3.setName("initial_activity");
			prop3.setRef(actI.getActivity());
			
			
			Property prop4 = new Property();
			prop4.setName("all_activities");
			
			Map map = new Map();
			
			List<Entry> entries = new ArrayList();
			
			List<FolderConfig> listActivities = foldercontentService.getAllActivitiesByIdScenario(scenario.getId_scenario());
			for(int j=0;j<listActivities.size();j++) {
				FolderConfig act = listActivities.get(j);
				
				Ref ref =new Ref();
				ref.setBean(act.getActivity());
				
				Entry entry = new Entry();
				entry.setKey(act.getActivity());
				entry.setRef(ref);
				
				entries.add(entry);
			}
				
			map.setEntry(entries);
			
			prop4.setMap(map);
			
			List<Property> props = new ArrayList();
			props.add(prop1);
			props.add(prop2);
			props.add(prop3);
			props.add(prop4);
			
			Bean bean = new Bean();
			bean.setProperty(props);
			bean.setId(scenario.getId_scenario());
			bean.setClassB("org.mss.swe.definitions.ProcessDefinition");
			
			beans.add(bean);
		}
		return beans;
	}
	
	
	//actor definition beans
	public List<Bean> actorsBean() {
		List<Profils> profilList = profilService.getAllActors();
		System.out.print(profilList);
		List<Bean> beans = new ArrayList();
		for(int i=0;i<profilList.size();i++) {
			
			Profils profil = profilList.get(i);
			
			Property prop1 = new Property();
			prop1.setName("actor_nameid");
			prop1.setValue(profil.getId_profil());
			
			Property prop2 = new Property();
			prop2.setName("actor_description");
			prop2.setValue(profil.getDescription_profil());
			
			List<Property> props = new ArrayList();
			props.add(prop1);
			props.add(prop2);
			
			Bean bean = new Bean();
			bean.setProperty(props);
			bean.setId(profil.getId_profil());
			bean.setClassB("org.mss.swe.definitions.ActorDefinition");
			
			
			
			beans.add(bean);
			
		}
		return beans;
	}
	
	//activity definition beans
	public List<Bean> activityBean() {
		String finale="true" ;
		String initial="true" ;
		
		List<FolderConfig> activityList = foldercontentService.getAllActivities();
	
		List<Bean> beans = new ArrayList();
		
		for(int i=0;i<activityList.size();i++) {
			List<Property> props = new ArrayList();
			FolderConfig activity = activityList.get(i);
			String type=activity.getType();
			if(type.equals("Closing"))
			{
				 finale ="true" ;
				 initial="false";
			}
			else if(type.equals("Initiating")) {
				 finale ="false" ;
				 initial="true";
			}
			else if(type.equals("Contributing")) {
				 finale ="false" ;
				 initial="false";
			}
				
			Property prop = new Property();
			prop.setName("activity_nameid");
			prop.setValue(activity.getActivity());
			Property prop1 = new Property();
			prop1.setName("activity_description");
			prop1.setValue(activity.getDefintion());
			Property prop2 = new Property();
			
			prop2.setName("duration");
			String dur[]= activity.getDuration().split("\\.");
			prop2.setValue(dur[0]);
			
			Property prop3 = new Property();
			prop3.setName("initial");
			prop3.setValue(initial);
			Property prop4 = new Property();
			prop4.setName("finale");
			prop4.setValue(finale);
			Property prop5 = new Property();
			prop5.setName("process_definition");
			prop5.setRef(activity.getScenario().toString());
			Property prop6 = new Property();
			prop6.setName("actor");
			prop6.setRef(activity.getProfils().toString());
			
		
			Property prop7 = new Property();
			
			Property prop8 = new Property();
			
            
			
			List<Entry> entriesAct = new ArrayList();
			
			if(activity.getNext_activity()!=null){
			String[] strArray = activity.getNext_activity().split("\\,");
			prop7.setName("next_activities");
	        Map map = new Map();
			for(int j=0;j<strArray.length;j++) {
				
				Ref ref =new Ref();
				ref.setBean(strArray[j]);
				
				Entry entry = new Entry();
				entry.setKey(String.valueOf(j+1));
				entry.setRef(ref);
				
				entriesAct.add(entry);
			    
		       
		      }
			map.setEntry(entriesAct);
			prop7.setMap(map);
			}
			
			
			
			List<Entry> entriesCon = new ArrayList();
			
			
			if(activity.getCondition()!=null) {
				String[] conArray = activity.getCondition().split("\\,");
				
				prop8.setName("conditions");
	            Map mapCon = new Map();
			for(int j=0;j<conArray.length;j++) {
				
				Entry entry1 = new Entry();
				entry1.setKey("flowType");
				entry1.setValue(conArray[j]);
				
				List<Entry> entriesCon1 = new ArrayList();
				entriesCon1.add(entry1);
				
				Map mapCon1 = new Map();
				mapCon1.setEntry(entriesCon1);
				
				Entry entry = new Entry();
				entry.setKey(String.valueOf(j+1));
				entry.setMap(mapCon1);
				
				
				
				entriesCon.add(entry);
			    
		       
		      }
			mapCon.setEntry(entriesCon);
			prop8.setMap(mapCon);
			}
			props.add(prop);
			props.add(prop1);
			props.add(prop2);
			props.add(prop3);
			props.add(prop4);
			props.add(prop5);

			
			props.add(prop6);
			if(prop7.getMap()!=null) {
			props.add(prop7);}
			
			if(prop8.getMap()!=null) {
			props.add(prop8);}

			
			Bean bean = new Bean();
			bean.setProperty(props);
			bean.setId(activity.getActivity());
			bean.setClassB("org.mss.swe.definitions.ActivityDefinition");
			
			
			
			beans.add(bean);
			
		}
		return beans;
	}
	
	//collect all beans 
	public  List<Bean> collectBeans( List<Bean> listeTotal,List<Bean> beansl){
		for(int i=0;i<beansl.size();i++) {
			listeTotal.add(beansl.get(i));
		}
		
		return listeTotal;
		
	}
	
	public String transformJsonToXML() throws JDOMException, IOException {
		List<Bean> totalBeans = new ArrayList();
		
		//get all the beans from the previous functions
		collectBeans(totalBeans,otherBean());
		
		collectBeans(totalBeans,scenarioBean());
		
		collectBeans(totalBeans,actorsBean());
		collectBeans(totalBeans,activityBean());
		Beans beans = new Beans();
		beans.setBean(totalBeans);
	
		//transform json data to xml
		JacksonXmlModule module = new JacksonXmlModule();
		module.setDefaultUseWrapper(false);
		XmlMapper xmlMapper = new XmlMapper(module);

	    String xml = xmlMapper.writeValueAsString(beans);
	    
	   //String prettyXml = new XMLOutputter(Format.getPrettyFormat()).outputString(new SAXBuilder().build(new StringReader(xml)));
	    
	    String prettyXml  = xml.replaceAll("><", ">\n<");;
	    
	    // save content in the xml fille
	    
	    
	    LocalDate localDateTime = LocalDate.now();
	    String dateModif=localDateTime.toString();
	    
	    File log = new File("C:\\Users\\User\\Desktop\\ENVIRONNEMENTS\\spades\\src\\main\\java\\org\\mss\\spades\\proceduremanagers\\modules\\workflowengine\\workflow-config.xml");
	    File newFile =new File("C:\\Users\\User\\Desktop\\ENVIRONNEMENTS\\spades\\src\\main\\java\\org\\mss\\spades\\proceduremanagers\\modules\\workflowengine\\workflow-config"+dateModif+".xml");
	    	    try{
	    	    if(log.exists()==false){
	    	            System.out.println("We had to make a new file.");
	    	            log.createNewFile();
	    	    }
	    	    else {
	    	    	log.renameTo(newFile);
	    	    	System.out.println("We had to rename the existant file and create a new one.");
	    	    	log.createNewFile();
	    	    }
	    	    PrintWriter out = new PrintWriter(new FileWriter(log, false)); 
	    	    System.out.println("Data saved in file.");
	    	    out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    	    
	    	    out.append("<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">");
	    	    out.append(prettyXml);
	    	    out.close();
	    	    }catch(IOException e){
	    	        System.out.println("COULD NOT LOG!!");
	    	    }
		
		return prettyXml;

		
		
	}
	
}


