package org.mss.bridge.to.spades.rest;

import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import org.mss.bridge.to.spades.domain.Profils;
import org.mss.bridge.to.spades.service.IProfilsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@Api(value="Profil",tags="REST - Profils")
public class ProfilsResource {
    private final Logger log = LoggerFactory.getLogger(ProfilsResource.class);
	private final IProfilsService profilsService;
	
    public ProfilsResource(IProfilsService profilsService) {
	
		this.profilsService = profilsService;
	}

	@PostMapping("/profils")
    public ResponseEntity<Profils> createProfil(@Valid @RequestBody Profils profil) throws URISyntaxException {
        log.debug("REST request to save Profil : {}", profil);
   
        Profils result = profilsService.save(profil);
        return ResponseEntity.created(new URI("/api/profils/" + result))
                .body(result);
    }

    @GetMapping("/profils")
    @ApiOperation(value = "Get all profils in  list")
    public  List<Profils> getAllCategories(Pageable pageable) {
        log.debug("REST request to get all profils");
        return profilsService.findAll();
    }
    @DeleteMapping("/profils/{id}")
    public ResponseEntity<Void> deleteProfil(@PathVariable String id) {
        log.debug("REST request to delete Profil : {}", id);
        profilsService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/profils/{id}")
    public ResponseEntity<Profils> getProfil(@PathVariable String id) {
        log.debug("REST request to get Categorie : {}", id);
       Optional< Profils> profils = profilsService.getProfilById(id);
        return ResponseEntity.ok().body(profils.get());
    }

    @PutMapping("/profils/{id}")
    public ResponseEntity<Profils> updateProfils(  @PathVariable(value = "id") String id ,@Valid @RequestBody Profils profil) throws URISyntaxException {
        log.debug("REST request to update Profil : {}", profil);

        Profils result = profilsService.updateProfil(id,profil);
        return ResponseEntity.ok()
                .body(result);
    }




}
