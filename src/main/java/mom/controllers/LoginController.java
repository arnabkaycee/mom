package mom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@RequestMapping("/success")
	@ResponseBody
	public String loginSuccess(){		
		return "{\"status\":\"true\"}";
	}
	
	@RequestMapping("/failure")
	@ResponseBody
	public String loginFailure(){		
		return "{\"status\":\"false\"}";
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(){		
		return "{\"status\":\"true\"}";
	}

}
