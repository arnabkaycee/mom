package mom.services;

import java.util.List;

import mom.dao.CommonDao;
import mom.models.Location;
import mom.models.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	
	@Autowired
	CommonDao commonDao;
	
	public List<String> getEmails(String email){
		return commonDao.getEmails(email);
	}
	
	public List<Location> getLocations(String location){
		return commonDao.getLocations(location);
	}
	
	public List<Project> getProjects(String project){
		return commonDao.getProjects(project);
	}

}
