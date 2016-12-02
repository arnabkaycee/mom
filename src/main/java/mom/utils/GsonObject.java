package mom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonObject {
	
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
			.excludeFieldsWithoutExposeAnnotation().create();
	
	private GsonObject(){
	}
	
	public static Gson getGson(){
		return gson;
	}

}
