package org.mss.bridge.to.spades.service;

import java.util.List;

import java.util.Optional;

import org.mss.bridge.to.spades.domain.Flux;

public interface IFluxService {

	public List<Flux> findAll();
	public Flux save(Flux flux);
	public void delete(String id);
	public Optional<Flux> getFluxById(String id);
    public Flux updateFlux(Flux flux);
	
}
