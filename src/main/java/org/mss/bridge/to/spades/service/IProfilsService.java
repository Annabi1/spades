package org.mss.bridge.to.spades.service;


import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

import org.mss.bridge.to.spades.domain.Profils;

public interface IProfilsService {
	public Profils save(Profils profils);

	public List<Profils> findAll();
	public void delete(String id);
    public Optional<Profils> getProfilById(String id);

	public Profils updateProfil(String id, @Valid Profils profil);
}
