package org.mss.bridge.to.spades.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mss.bridge.to.spades.domain.Flux;
import org.mss.bridge.to.spades.domain.Scenario;
import org.mss.bridge.to.spades.service.FluxServiceImpl;
import org.mss.bridge.to.spades.service.ScenarioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Flux",tags="REST - Flux")
public class FluxResource {
	 private final Logger log = LoggerFactory.getLogger(Flux.class);
	  private final FluxServiceImpl fluxService;
	public FluxResource(FluxServiceImpl fluxService) {
		super();
		this.fluxService = fluxService;
	}

	@GetMapping("/flux")
	@ApiOperation(value = "list of Flux")
    public List<Flux> getAllFlux( )  {
        log.debug("REST request to get all  Flux : {}");
           return fluxService.findAll();
    }
	@PostMapping("/flux")
    @ApiOperation(value = "Create a flux")
    public ResponseEntity<Flux> createFlux(@Valid @RequestBody Flux flux) throws URISyntaxException {
        log.debug("REST request to save Flux : {}", flux);
   
        Flux result = fluxService.save(flux);
        return ResponseEntity.created(new URI("/api/scenarios/" + result))
                .body(result);
    }
	  @DeleteMapping("/flux/{id}")
	    @ApiOperation(value = "delete  Scenario")
	    public ResponseEntity<Void> deleteFlux(@PathVariable String id) {
	        log.debug("REST request to delete flux : {}", id);
	        fluxService.delete(id);
	        return ResponseEntity.noContent().build();
	    }

		
		  @PutMapping("/flux")
		  @ApiOperation(value = "update  Flux") 
		  public ResponseEntity<Flux>updateFlux(@Valid @RequestBody Flux flux) throws
		  URISyntaxException { log.debug("REST request to update Flux : {}",
		  flux);
		  Flux result = fluxService.updateFlux(flux); return
		  ResponseEntity.ok() .body(result); }
		 
	  @GetMapping("/flux/{id}")
	    public ResponseEntity<Flux> getFlux(@PathVariable String id) {
	        log.debug("REST request to get Flux : {}", id);
	       Optional< Flux> flux = fluxService.getFluxById(id);
	        return ResponseEntity.ok().body(flux.get());
	    }

}
