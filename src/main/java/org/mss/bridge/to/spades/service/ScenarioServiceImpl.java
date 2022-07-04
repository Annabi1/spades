package org.mss.bridge.to.spades.service;

import java.util.List;


import java.util.Optional;

import javax.transaction.Transactional;

import org.mss.bridge.to.spades.domain.Scenario;
import org.mss.bridge.to.spades.repository.ScenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class ScenarioServiceImpl implements IScenarioService {
	@Autowired
      private   ScenarioRepository scenarioRepo;
      


	@Override
	public Scenario save(Scenario scenario) {
		// TODO Auto-generated method stub
		return  scenarioRepo.save(scenario);
	}



	@Override
	public List<Scenario> getAllScenario() {
		// TODO Auto-generated method stub
		return scenarioRepo.findAll();
	}



	@Override
	public void delete(String id) {
		Scenario scenario=scenarioRepo.getOne(id);
		if(scenario.getId_scenario()==id)
		{
		scenarioRepo.deleteById(id);		}
	}



	@Override
	public Optional<Scenario> getScenarioById(String id) {
		// TODO Auto-generated method stub
		return scenarioRepo.findById(id) ;
	}



	@Override
	public Scenario updateScenario(String id_scenario, Scenario scenario) {
		Optional<Scenario>  scenarioCreated=scenarioRepo.findById(id_scenario);
		scenarioCreated.get().setDescription_scenario(scenario.getDescription_scenario());
		scenarioCreated.get().setId_scenario(scenario.getId_scenario());
		scenarioCreated.get().setForms(scenario.getForms());
		scenario=scenarioCreated.get();
		scenarioRepo.save(scenario);
		return scenario;
	}







}
