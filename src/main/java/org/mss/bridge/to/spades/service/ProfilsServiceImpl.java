package org.mss.bridge.to.spades.service;




import java.util.List;


import java.util.Optional;

import javax.transaction.Transactional;

import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.repository.ProfilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class ProfilsServiceImpl  implements IProfilsService{
	@Autowired
	private ProfilsRepository profilsRepo;


	@Override
	public Profils save(Profils profils) {
		// TODO Auto-generated method stub
		return profilsRepo.save(profils);
	}

	@Override
	public List<Profils> findAll() {
		// TODO Auto-generated method stub
		return   profilsRepo.findAll( );
	}

	@Override
	public void delete(String id) {
		Profils profil=profilsRepo.getOne(id);
		if(profil.getId_profil()==id)
		{
			profilsRepo.deleteById(id);

		}
	}
	
       	
    	 
    	
    	 

			/*
			 * public List<String> getAllActors (Actors actor ){ List<Actors> liste=new
			 * ArrayList<>();
			 */ 
     
     
     public List<Profils> getAllActors()
     {
         return (List<Profils>)
            profilsRepo.findAll();
     }
	
	

	@Override
	public Optional<Profils> getProfilById(String id) {
		// TODO Auto-generated method stub
		return profilsRepo.findById(id);
	}

	@Override
	public Profils updateProfil(String id,Profils profil) {
		
		Optional<Profils> profilupdated=profilsRepo.findById(id);
		profilupdated.get().setId_profil(profil.getId_profil());
		profilupdated.get().setDescription_profil(profil.getDescription_profil());
		profil=profilupdated.get();
		return profilsRepo.save(profil);}
	

	
}
