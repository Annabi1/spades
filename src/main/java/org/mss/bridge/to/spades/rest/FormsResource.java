package org.mss.bridge.to.spades.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mss.bridge.to.spades.domain.Forms;
import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.service.FormsServiceImpl;
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
@Api(value="Form",tags="REST - Forms")
public class FormsResource {
	  private final Logger log = LoggerFactory.getLogger(FormsResource.class);
	  private final FormsServiceImpl formsService;
	public FormsResource(FormsServiceImpl formsService) {
		
		this.formsService = formsService;
	}
	@PostMapping("/forms")
    @ApiOperation(value = "Create a Form")
    public ResponseEntity<Forms> createForm(@Valid @RequestBody Forms form) throws URISyntaxException {
        log.debug("REST request to save Form : {}", form);
   
        Forms result = formsService.save(form);
        return ResponseEntity.created(new URI("/api/profils/" + result))
                .body(result);
    }
	@GetMapping("/forms")
    @ApiOperation(value = "Get the list of  Forms")
    public List <Forms> getAllForms( Pageable pageable) {
        log.debug("REST request to get all Profils ");
        return formsService.findAll();
	}
	   @DeleteMapping("/forms/{id}")
	    @ApiOperation(value = "delete a form")
	    public ResponseEntity<Void> deleteForm(@PathVariable String id) {
	        log.debug("REST request to delete Form : {}", id);
	        formsService.delete(id);
	        return ResponseEntity.noContent().build();
	    }
	   @GetMapping("/forms/{id}")
	    @ApiOperation(value = "get a form by id")

	    public ResponseEntity<Forms> getFomById(@PathVariable String id) {
	        log.debug("REST request to get Form by id : {}", id);
	        Optional<Forms> form = formsService.getFormById(id);
	        return ResponseEntity.ok().body(form.get());
	    }
	   
	   
	    @PutMapping("/forms/{id}")
	    @ApiOperation(value = "update form")
	    public ResponseEntity<Forms> updateForm(@PathVariable(value = "id") String id ,@Valid @RequestBody Forms form) throws URISyntaxException {
	        log.debug("REST request to update Profil : {}", form);

	        Forms result = formsService.updateForm(form,id);
	        return ResponseEntity.ok()
	                .body(result);
	    }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
}
