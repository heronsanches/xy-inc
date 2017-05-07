package com.xyinc.zup.rest.application.em;

import com.fasterxml.jackson.core.JsonParseException;
import com.xyinc.ejb.utils.ResponseEnum;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Heron Sanches
 */
@Provider
public class JsonJacksonEM implements ExceptionMapper<JsonParseException>{


   @Override
   public Response toResponse(JsonParseException e) {
      return Response.ok(new MessageJB(ResponseEnum.ERROR, "Please verify the Json format.")).build();
   }
   

}