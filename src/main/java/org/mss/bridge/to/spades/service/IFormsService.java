package org.mss.bridge.to.spades.service;

import java.util.List;

import java.util.Optional;

import org.mss.bridge.to.spades.domain.Forms;

public interface IFormsService {
	public List<Forms> findAll();
	public Forms save(Forms forms);
	public void delete(String id);
	public Optional<Forms> getFormById(String id);
    public Forms updateForm(Forms form,String id);
}
