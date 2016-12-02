package mom.controllers;

import java.util.List;

import mom.models.Location;
import mom.models.Project;
import mom.services.CommonService;
import mom.utils.GsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CommonController {

	@Autowired
	CommonService commonService;
	
	@RequestMapping(value="/email", method=RequestMethod.POST, produces="application/json")
	public String getEmails(@RequestParam("email") String email){
		List<String> emailList = commonService.getEmails(email);
		Gson gson = GsonObject.getGson();
		return gson.toJson(emailList);
	}
	
	@RequestMapping(value="/location", method=RequestMethod.POST, produces="application/json")
	public String getLocations(@RequestParam("location") String location){
		List<Location> locationList = commonService.getLocations(location);
		Gson gson = GsonObject.getGson();
		return gson.toJson(locationList);
	}
	
	@RequestMapping(value="/project", method=RequestMethod.POST, produces="application/json")
	public String getProjects(@RequestParam("project") String project){
		List<Project> projectList = commonService.getProjects(project);
		Gson gson = GsonObject.getGson();
		return gson.toJson(projectList);
	}
	
}
