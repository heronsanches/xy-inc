package com.xyinc.zup.rest.application.poi.resources;

import com.xyinc.ejb.jsonbinding.PoiJB;
import com.xyinc.ejb.sb.stateless.PoisSBI;
import com.xyinc.ejb.utils.ResponseEnum;
import com.xyinc.zup.rest.application.em.MessageJB;
import com.xyinc.zup.rest.application.poi.resources.jsonbinding.poi.InsertJBI;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Heron Sanches
 */
@Path("/")
public class PoiR {
   
   public static final String COORDINATE_MSG_CONSTRAINT = "The coordinate values must be a no negative number.";
   public static final String DISTANCE_MSG_CONSTRAINT = "The distance value must be a integer grather than 0.";
   public static final String NAME_MSG_CONSTRAINT = "The name value must be a no empty value.";

   @EJB
   private PoisSBI poisSBI;
   
   
   @GET
   @Path("all")
   @Produces(MediaType.APPLICATION_JSON)
   public PoiJB getAll(){
      return poisSBI.listAll();
   }
   
   
   @GET
   @Path("all/by-distance-range")
   @Produces(MediaType.APPLICATION_JSON)
   public PoiJB getAllByDistanceRange(@QueryParam("x") int x, @QueryParam("y") int y, @QueryParam("distance") int distance){
      
      if(x < 0 || y < 0)
         throw new WebApplicationException(Response.ok(new MessageJB(ResponseEnum.ERROR, COORDINATE_MSG_CONSTRAINT)).build());
      
      if(distance < 1)      
         throw new WebApplicationException(Response.ok(new MessageJB(ResponseEnum.ERROR, DISTANCE_MSG_CONSTRAINT)).build());
      
      return poisSBI.findByDistance(x, y, distance);
   
   }
   
   
   @POST
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   public PoiJB insert(InsertJBI i){
      
      if(i.getX() < 0 || i.getY() < 0)
         throw new WebApplicationException(Response.ok(new MessageJB(ResponseEnum.ERROR, COORDINATE_MSG_CONSTRAINT)).build());
      
      if(i.getName() == null || i.getName().trim().isEmpty())      
         throw new WebApplicationException(Response.ok(new MessageJB(ResponseEnum.ERROR, NAME_MSG_CONSTRAINT)).build());
      
      return poisSBI.insert(i.getX(), i.getY(), i.getName());
      
   }
   
   
}