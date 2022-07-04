package org.mss.bridge.to.spades.service;




import java.util.List;
import java.util.Optional;

import org.mss.bridge.to.spades.domain.Forms;
import org.mss.bridge.to.spades.repository.FormsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FormsServiceImpl implements IFormsService {
	@Autowired
	private FormsRepository formsRepo;
	
	@Override
	public List<Forms> findAll() {
		// TODO Auto-generated method stub
		return formsRepo.findAll();
	}

	@Override
	public Forms save(Forms forms) {
		// TODO Auto-generated method stub
		return formsRepo.save(forms);
	}

	public void delete(String id) {
Forms form=	formsRepo.getOne(id);
if(form.getId_form()==id) {
	formsRepo.deleteById(id);
}

	}

	@Override
	public Optional<Forms> getFormById(String id) {
		// TODO Auto-generated method stub
		return formsRepo.findById(id);
	}

	@Override
    public Forms updateForm(Forms form,String id) {
		Optional<Forms> formSearch=formsRepo.findById(id);
			formSearch.get().setId_form(form.getId_form());
			formSearch.get().setForm_path(form.getForm_path());
			form=formSearch.get();
			formsRepo.save(form);
		
		return form;
	}
	}
