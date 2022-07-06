package org.mss.bridge.to.spades.rest;

import java.net.URI;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mss.bridge.to.spades.domain.Forms;
import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.domain.Scenario;
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
@Api(value="Scenario",tags="REST - Scenarios")
public class ScenarioResource {
	  private final Logger log = LoggerFactory.getLogger(ScenarioResource.class);
	  private final ScenarioServiceImpl scenarioService;
	public ScenarioResource(ScenarioServiceImpl scenarioService) {
		super();
		this.scenarioService = scenarioService;
	}
	@PostMapping("/scenarios")
    @ApiOperation(value = "Create a Scenario")
    public ResponseEntity<Scenario> createForm(@Valid @RequestBody Scenario scenario) throws URISyntaxException {
        log.debug("REST request to save Scenario : {}", scenario);
   
        Scenario result = scenarioService.save(scenario);
        return ResponseEntity.created(new URI("/api/scenarios/" + result))
                .body(result);
    }
	@GetMapping("/scenarios")
    @ApiOperation(value = "list of Scenario")
    public List<Scenario> getAllScenarios()  {
        log.debug("REST request to get all  Scenario : {}");
           return scenarioService.getAllScenario();
    }
	  @DeleteMapping("/scenarios/{id}")
	    @ApiOperation(value = "delete  Scenario")
	    public ResponseEntity<Void> deleteScenario(@PathVariable String id) {
	        log.debug("REST request to delete Profil : {}", id);
	        scenarioService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	  @PutMapping("/scenarios/{id}")
	    @ApiOperation(value = "update  Scenario")
	    public ResponseEntity<Scenario> updateScenario
	    (@PathVariable(value = "id") String id ,@Valid @RequestBody Scenario scenario) throws URISyntaxException {
	        log.debug("REST request to update Profil : {}", scenario);
	        Scenario result = scenarioService.updateScenario(id,scenario);
	        return ResponseEntity.ok()
	                .body(result);
	    }
	  @GetMapping("/scenarios/{id}")
	    public ResponseEntity<Scenario> getScenario(@PathVariable String id) {
	        log.debug("REST request to get Scenario : {}", id);
	       Optional< Scenario> scenario = scenarioService.getScenarioById(id);
	        return ResponseEntity.ok().body(scenario.get());
	    }

}
