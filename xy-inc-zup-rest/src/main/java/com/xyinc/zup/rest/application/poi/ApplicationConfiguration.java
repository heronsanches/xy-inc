package com.xyinc.zup.rest.application.poi;

import com.xyinc.zup.rest.application.em.JsonJacksonEM;
import com.xyinc.zup.rest.application.poi.resources.PoiR;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Heron Sanches
 */
@ApplicationPath("poi")
public class ApplicationConfiguration extends Application{
   
   
   @Override
   public Set<Class<?>> getClasses() {
      
      Set<Class<?>> resources = new HashSet<>();
      resources.add(PoiR.class);
      resources.add(JsonJacksonEM.class);
      return resources; 
      
   }
   

}