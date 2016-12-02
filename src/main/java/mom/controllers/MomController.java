package mom.controllers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import mom.models.MomData;
import mom.models.MomListData;
import mom.services.MomService;
import mom.utils.GsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class MomController {
	
	@Autowired
	MomService momService;

	@RequestMapping(value="/updatemom", method=RequestMethod.POST, produces="application/json")
	public String updateMom(@RequestBody String momData){
		Gson gson = GsonObject.getGson();
		MomData momDataDemo = null;
		momDataDemo = gson.fromJson(momData, MomData.class);
		momService.addMom(momDataDemo);
		return "{\"status\":\"true\"}";
	}
	
	@RequestMapping(value="/getmom", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String getMom(@RequestParam("momId") String momId){
		MomData momData = momService.getMom(Long.parseLong(momId));
		Gson gson = GsonObject.getGson();
		return gson.toJson(momData);
	}
	
	@RequestMapping(value="/getmoms", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String getMoms(@RequestBody String searchData){
		Gson gson = GsonObject.getGson();
		MomListData momSearchData = gson.fromJson(searchData, MomListData.class);
		List<MomListData> momList = null;
		try {
			momList = momService.getMoms(momSearchData);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IntrospectionException e) {
		}
		return gson.toJson(momList);
	}
	
	@RequestMapping(value="/addmom", method=RequestMethod.POST, produces="application/json")
	public String addMom(@RequestBody String momData){
		Gson gson = GsonObject.getGson();
		MomData momDataDemo = null;
		momDataDemo = gson.fromJson(momData, MomData.class);
		momService.addMom(momDataDemo);
		return "{\"status\":\"true\"}";
	}
	
}
