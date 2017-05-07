package com.xyinc.zup.rest.test.application.poi.resources;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.jsonbinding.PoiAuxJB;
import com.xyinc.ejb.jsonbinding.PoiJB;
import com.xyinc.ejb.sb.dao.PoiDAOI;
import com.xyinc.ejb.sb.stateless.PoisSBI;
import com.xyinc.ejb.utils.PersistenceContextConstants;
import com.xyinc.ejb.utils.ResponseEnum;
import com.xyinc.zup.rest.application.em.JsonJacksonEM;
import com.xyinc.zup.rest.application.em.MessageJB;
import com.xyinc.zup.rest.application.poi.ApplicationConfiguration;
import com.xyinc.zup.rest.application.poi.resources.PoiR;
import com.xyinc.zup.rest.application.poi.resources.jsonbinding.poi.InsertJBI;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Heron Sanches
 */
@RunWith(Arquillian.class)

public class PoiRTest {   
   
   private static final String RESOURCE_PREFIX = ApplicationConfiguration.class.getAnnotation(ApplicationPath.class).value().substring(0);
   private static final Client CLIENT = ClientBuilder.newBuilder().build();

   @ArquillianResource
   URL deploymentUrl;
   
   @Resource
   private UserTransaction ut;
   
   @PersistenceContext(unitName = PersistenceContextConstants.XYINC_POI)
   private EntityManager em;
   

   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class)
              .addPackage(PoiR.class.getPackage())
              .addPackage(ApplicationConfiguration.class.getPackage())
              .addPackage(JsonJacksonEM.class.getPackage())
              .addPackage(InsertJBI.class.getPackage())
              .addPackage(PoiJB.class.getPackage())
              .addPackage(PoisSBI.class.getPackage())
              .addPackage(PoiDAOI.class.getPackage())
              .addPackage(Poi.class.getPackage())
              .addPackage(MessageJB.class.getPackage())
              .addPackage(ResponseEnum.class.getPackage())
              //.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
              .addAsResource("META-INF/persistence.xml")
              .addAsWebInfResource(new File("src/main/webapp", "WEB-INF/jboss-web.xml"));

   }
   
   
   @AfterClass
   public static void releaseResources(){
      PoiRTest.CLIENT.close();
   }

   
   /**This method clean the database before initialize the tests.*/
   @Test
   @InSequence(1)
   public void destroyData() throws Exception{
      
      assertNotEquals(null, ut);
      assertNotEquals(null, em);
      ut.begin();
      em.createQuery("DELETE FROM Poi").executeUpdate();
      ut.commit();
      
   }
   
   
   /**
    * Test of insert method, of class PoiR.
    */
   @Test
   @InSequence(2)
   @RunAsClient
   public void testInsert() throws Exception {

      /********************testing successfully insert - it has to return ResponseEnum.OK*/
      InsertJBI iJBI = new InsertJBI();
      iJBI.setName("Lanchonete");
      iJBI.setX(27);
      iJBI.setY(12);
      
      Response res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      PoiJB poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Posto");
      iJBI.setX(31);
      iJBI.setY(18);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Joalheria");
      iJBI.setX(15);
      iJBI.setY(12);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Floricultura");
      iJBI.setX(19);
      iJBI.setY(21);
      
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Pub");
      iJBI.setX(12);
      iJBI.setY(8);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Supermercado");
      iJBI.setX(23);
      iJBI.setY(6);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      res.close();
      iJBI.setName("Churrascaria");
      iJBI.setX(28);
      iJBI.setY(2);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());      

      
      /********************testing no successfully insert - it has to return ResponseEnum.ALREADY_EXISTIS*/
      res.close();
      iJBI.setName("Duplicated PK");
      iJBI.setX(19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.ALREADY_EXISTIS, poiJB.getStatus());
      res.close();
      
      
      /********************testing validation fields*/
      //name cannot be null
      res.close();
      iJBI.setName(null);
      iJBI.setX(19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      MessageJB msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.NAME_MSG_CONSTRAINT, msgJB.getMessage());
      
      //name cannot be empty
      res.close();
      iJBI.setName("    ");
      iJBI.setX(19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.NAME_MSG_CONSTRAINT, msgJB.getMessage());
      
      //name cannot be empty
      res.close();
      iJBI.setName("");
      iJBI.setX(19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.NAME_MSG_CONSTRAINT, msgJB.getMessage());
      
      //"x" must be a no negative number
      res.close();
      iJBI.setName("Something");
      iJBI.setX(-19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //"y" must be a no negative number
      res.close();
      iJBI.setName("Something");
      iJBI.setX(19);
      iJBI.setY(-21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //"x" must be a no negative number
      res.close();
      iJBI.setName("Something");
      iJBI.setX(-19);
      iJBI.setY(21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //"x" and "y" must be a no negative number
      res.close();
      iJBI.setName("Something");
      iJBI.setX(-19);
      iJBI.setY(-21);
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX)
                           .request().accept("application/json").post(Entity.json(iJBI));
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
   }
   
   
   /**
    * Test of getAll method, of class PoiR.
    */
   @Test
   @InSequence(3)
   @RunAsClient
   public void testGetAll() throws Exception {

      Response res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all")
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      
      res.bufferEntity();
      PoiJB poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      assertEquals(7, poiJB.getPois().size());

      Map<String,String> mapExpRes = new HashMap<>(7);
      mapExpRes.put("27;12", "Lanchonete");
      mapExpRes.put("15;12", "Joalheria");
      mapExpRes.put("12;8", "Pub");
      mapExpRes.put("23;6", "Supermercado");
      mapExpRes.put("31;18", "Posto");
      mapExpRes.put("19;21", "Floricultura");
      mapExpRes.put("28;2", "Churrascaria");

      Set<String> set = new HashSet<>(7);
      String coordinates = "";

      //it veirifies if all returned POIs are distincts and if them belongs to the map with the expected result
      for(PoiAuxJB poi: poiJB.getPois()){

         coordinates = poi.getX()+";"+poi.getY();
         assertEquals(true, set.add(coordinates));
         assertEquals(true, mapExpRes.containsKey(coordinates) && mapExpRes.get(coordinates).contentEquals(poi.getName()));

      }
      
      res.close();
      
   }
   

   /**
    * Test of getAllByDistanceRange method, of class PoiR.
    */
   @Test
   @InSequence(4)
   @RunAsClient
   public void testGetAllByDistanceRange() {
      
      /*************testing if the data returned is correct**************/
      Response res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", 10)
                           .queryParam("distance", 10)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      PoiJB poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      assertEquals(4, poiJB.getPois().size());
      Map<String,String> mapExpRes = new HashMap<>(4);
      mapExpRes.put("27;12", "Lanchonete");
      mapExpRes.put("15;12", "Joalheria");
      mapExpRes.put("12;8", "Pub");
      mapExpRes.put("23;6", "Supermercado");
      
      Set<String> set = new HashSet<>(4);
      String coordinates = "";
      
      //it veirifies if all returned POIs are distincts and if them belongs to the map with the expected result
      for(PoiAuxJB poi: poiJB.getPois()){
         
         coordinates = poi.getX()+";"+poi.getY();
         assertEquals(true, set.add(coordinates));
         assertEquals(true, mapExpRes.containsKey(coordinates) && mapExpRes.get(coordinates).contentEquals(poi.getName()));
         
      }
      
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", 10)
                           .queryParam("distance", 5)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      assertEquals(1, poiJB.getPois().size());
      assertEquals(true, poiJB.getPois().get(0).getX() == 23 && poiJB.getPois().get(0).getY() == 6
         && poiJB.getPois().get(0).getName().contentEquals("Supermercado"));
      
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", 10)
                           .queryParam("distance", 4)
                           .request().accept("application/json").get();

      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.NO_EXISTS, poiJB.getStatus());
      assertEquals(0, poiJB.getPois().size());
      res.close();
      
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 0)
                           .queryParam("y", 0)
                           .queryParam("distance", 10)
                           .request().accept("application/json").get();

      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      poiJB = res.readEntity(PoiJB.class);
      assertEquals(ResponseEnum.NO_EXISTS, poiJB.getStatus());
      assertEquals(0, poiJB.getPois().size());
      
      
      /*************testing query parameter validation**************/
      //testing query parameter "x", it has to be a no negative integer
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", -20)
                           .queryParam("y", 10)
                           .queryParam("distance", 4)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      MessageJB msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //testing query parameter "y", it has to be a no negative integer
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", -10)
                           .queryParam("distance", 4)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //testing query parameter  "x" and "y", them has to be a no negative integer
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", -20)
                           .queryParam("y", -10)
                           .queryParam("distance", 4)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.COORDINATE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //testing query parameter "distance", it has to be a positive integer
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", 10)
                           .queryParam("distance", 0)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.DISTANCE_MSG_CONSTRAINT, msgJB.getMessage());
      
      //testing query parameter "distance", it has to be a positive integer
      res.close();
      res = CLIENT.target(deploymentUrl.toString()+RESOURCE_PREFIX+"/all/by-distance-range")
                           .queryParam("x", 20)
                           .queryParam("y", 10)
                           .queryParam("distance", -99)
                           .request().accept("application/json").get();
      
      assertEquals(Response.Status.OK.getStatusCode(), res.getStatus());
      res.bufferEntity();
      msgJB = res.readEntity(MessageJB.class);
      assertEquals(ResponseEnum.ERROR, msgJB.getStatus());
      assertEquals(PoiR.DISTANCE_MSG_CONSTRAINT, msgJB.getMessage());
      res.close();
      
   }
   
   
}