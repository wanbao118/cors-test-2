package edu.xust.aws;

import java.io.File;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class CorsTestController {

	@RequestMapping(path="/html/{filename}", method = RequestMethod.GET,produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<InputStreamResource> load(@PathVariable("filename") String filename) throws Exception {
		
//		InputStream inputStream = CorsTestController.class.getResourceAsStream(filename);
		ClassPathResource imgFile = new ClassPathResource("static" + File.separator + filename);
		InputStreamResource data = new InputStreamResource(imgFile.getInputStream());
		return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(data);
	}
	
	
	
}
