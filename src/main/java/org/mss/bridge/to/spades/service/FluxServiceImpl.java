package org.mss.bridge.to.spades.service;




import java.util.List;
import java.util.Optional;

import org.mss.bridge.to.spades.domain.Flux;
import org.mss.bridge.to.spades.repository.FluxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FluxServiceImpl implements IFluxService {
	@Autowired
	private FluxRepository fluxRepo;
	

	@Override
	public List<Flux> findAll() {
		// TODO Auto-generated method stub
		return fluxRepo.findAll();
	}

	@Override
	public Flux save(Flux flux) {
		// TODO Auto-generated method stub
		return fluxRepo.save(flux);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		fluxRepo.deleteById(id);
	}

	@Override
	public Optional<Flux> getFluxById(String id) {
		// TODO Auto-generated method stub
		return fluxRepo.findById(id);
	}

	@Override
	public Flux updateFlux(Flux flux) {
     Flux fluxUpdated=new Flux();
     fluxUpdated=flux;
     return fluxRepo.save(fluxUpdated);

     
	}
	

	

}
