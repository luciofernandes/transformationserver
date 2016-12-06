package br.ufpe.cin.transformationserver.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.ufpe.cin.transformationserver.domain.Transformation;
import br.ufpe.cin.transformationserver.service.TransformationService;

@Controller
@RequestMapping("/transformation")
public class TransformationController {

	@Autowired
	private TransformationService transformationService;

	@RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody String getTransformationCode(@RequestBody Transformation t, HttpServletRequest request){
		//is client behind something?
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		t.setIpAddress(ipAddress);
	
		int nameHash = new Date().toString().hashCode();
		t.setSourceName(String.valueOf(nameHash));

		String out = "";
		if(t.getType().equals("EERToRelational")){
			out = transformationService.getTransformationCodeEER2Relational(t);
		}
		if(t.getType().equals("RMMToRelational")){
			out = transformationService.getTransformationCodeRMM2Relational(t);
		}						
		if(t.getType().equals("SEERToRelational")){
			out = transformationService.getTransformationCodeSEER2Relational(t);
		}
		return out;
	}
	
}
