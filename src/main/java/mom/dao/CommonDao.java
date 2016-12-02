package mom.dao;

import java.util.List;

import mom.models.Location;
import mom.models.Project;

public interface CommonDao {

		public <T> T persist(final T t);
		
		public <T> T merge(T t);
		
		public String getSequenceNo(Integer length);
		
		public List<String> getEmails(String email);
		
		public List<Location> getLocations(String location);
		
		public List<Project> getProjects(String project);
}
