package mom.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mom.entities.Mom;
import mom.models.MomListData;

public interface MomDao {
	
	public void insertMom(Mom mom);
	
	public Mom getMom(Long momId);
	
	public List<MomListData> getMoms(Map<String, Object> searchMap);
	
}
