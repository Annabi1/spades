package org.mss.bridge.to.spades.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mss.bridge.to.spades.domain.Flux;
import org.mss.bridge.to.spades.domain.FolderConfig;
import org.mss.bridge.to.spades.service.FluxServiceImpl;
import org.mss.bridge.to.spades.service.FolderConfigServiceImpl;
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
@Api(value="folder_content_user_activity_config",tags="REST - folder_content_user_activity_config")
public class FolderConfigResource {

	private final Logger log = LoggerFactory.getLogger(FolderConfigResource.class);
	  private final FolderConfigServiceImpl folderService;
	public FolderConfigResource(FolderConfigServiceImpl folderService) {
		super();
		this.folderService = folderService;
	}

	@GetMapping("/folder")
	@ApiOperation(value = "list of folder_content_user_activity_config")
    public List<FolderConfig> getAllFolder( )  {
        log.debug("REST request to get all  Folder : {}");
           return folderService.findAll();
    }

	@PostMapping("/folder")
    @ApiOperation(value = "Create a folder_content_user_activity_config")
    public ResponseEntity<FolderConfig> createFolder(@Valid @RequestBody FolderConfig folderConfig) throws URISyntaxException {
        log.debug("REST request to save folder_content_user_activity_config : {}", folderConfig);
   
        FolderConfig result = folderService.save(folderConfig);
        return ResponseEntity.created(new URI("/api/scenarios/" + result))
                .body(result);
    }
	  @DeleteMapping("/folder/{id}")
	    @ApiOperation(value = "delete  folder_content_user_activity_config")
	    public ResponseEntity<Void> deleteFolder(@PathVariable String id) {
	        log.debug("REST request to delete folder_content_user_activity_config : {}", id);
	        folderService.delete(id);
	        return ResponseEntity.noContent().build();
	    }

		
		  @PutMapping("/folder")
		  @ApiOperation(value = "update  folder_content_user_activity_config") 
		  public ResponseEntity<FolderConfig>updateFolder(@PathVariable(value = "id") String id,@Valid @RequestBody FolderConfig folder) throws
		  URISyntaxException { log.debug("REST request to update folder_content_user_activity_config : {}",
		  folder);
		  FolderConfig result = folderService.updateFolderConfig(folder,id); return
		  ResponseEntity.ok() .body(result); }
		 
	  @GetMapping("/folder/{id}")
	  @ApiOperation(value = "get  folder_content_user_activity_config") 
	    public ResponseEntity<FolderConfig> getFolder(@PathVariable String id) {
	        log.debug("REST request to get folder_content_user_activity_config : {}", id);
	       Optional< FolderConfig> flux = folderService.getFolderConfigById(id);
	        return ResponseEntity.ok().body(flux.get());
	    }

	
}
