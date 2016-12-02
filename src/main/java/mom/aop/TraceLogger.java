package mom.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public class TraceLogger {
	
	final static Logger logger = Logger.getLogger(TraceLogger.class);
	
	public void logBefore(JoinPoint joinPoint){
	    if(logger.isDebugEnabled()){
	    	logger.debug("Entering into "+ joinPoint.getSignature().getName() + " method");	
	    }
	}
	
	public void logAfter(JoinPoint joinPoint){
		if(logger.isDebugEnabled()){
			logger.debug("Exiting from "+ joinPoint.getSignature().getName() + " method");
	    }
	}
	
}
