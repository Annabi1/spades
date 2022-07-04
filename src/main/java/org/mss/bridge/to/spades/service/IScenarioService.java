package org.mss.bridge.to.spades.service;

import java.util.List;


import java.util.Optional;

import org.mss.bridge.to.spades.domain.Scenario;

public interface IScenarioService {

	
	public Scenario save(Scenario Scenarios);
	public List<Scenario>getAllScenario();
	public void delete (String id);
	public Optional<Scenario> getScenarioById(String id);
	public Scenario updateScenario(String id,Scenario Scenarios);
}
