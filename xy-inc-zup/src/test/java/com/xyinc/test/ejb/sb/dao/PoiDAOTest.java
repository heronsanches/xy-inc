package com.xyinc.test.ejb.sb.dao;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.entity.PoiPK;
import com.xyinc.ejb.jsonbinding.PoiAuxJB;
import com.xyinc.ejb.jsonbinding.PoiJB;
import com.xyinc.ejb.sb.dao.PoiDAOI;
import com.xyinc.ejb.utils.PersistenceContextConstants;
import com.xyinc.ejb.utils.ResponseEnum;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author Heron Sanches
 */
@RunWith(Arquillian.class)
public class PoiDAOTest {

   @EJB
   private PoiDAOI poiDAOI;
   
   @PersistenceContext(unitName = PersistenceContextConstants.XYINC_POI)
   private EntityManager em;
   
   @Resource
   private UserTransaction ut;
   
   
   @Deployment
   public static Archive<?> createTestArchive() {
      
      return ShrinkWrap.create(JavaArchive.class)
              .addPackage(PoiDAOI.class.getPackage())
              .addPackage(Poi.class.getPackage())
              .addPackage(ResponseEnum.class.getPackage())
              .addPackage(PoiJB.class.getPackage())
              .addAsResource("META-INF/persistence.xml");
      
   }
   
   
   private void destroyData() throws Exception{
      
      ut.begin();
      em.createQuery("DELETE FROM Poi").executeUpdate();
      ut.commit();
      
   }
   
   
   private void initializeData() throws Exception{
      
      ut.begin();
      poiDAOI.insert(new Poi(new PoiPK(27, 12), "Lanchonete"));
      poiDAOI.insert(new Poi(new PoiPK(31, 18), "Posto"));
      poiDAOI.insert(new Poi(new PoiPK(15, 12), "Joalheria"));
      poiDAOI.insert(new Poi(new PoiPK(19, 21), "Floricultura"));
      poiDAOI.insert(new Poi(new PoiPK(12, 8), "Pub"));
      poiDAOI.insert(new Poi(new PoiPK(23, 6), "Supermercado"));
      poiDAOI.insert(new Poi(new PoiPK(28, 2), "Churrascaria"));
      ut.commit();
      
   }
   
   
   /**
    * Test of insert method, of class PoiDAO.
    */
   @Test
   @InSequence(1)
   public void testInsert() throws Exception {
      
      destroyData();
      PoiJB poiJB;
      //testing successfully insert - it has to return ResponseEnum.OK
      Poi poi = new Poi(new PoiPK(27, 12), "Lanchonete");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      poi = new Poi(new PoiPK(31, 18), "Posto");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());

      poi = new Poi(new PoiPK(15, 12), "Joalheria");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      poi = new Poi(new PoiPK(19, 21), "Floricultura");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      poi = new Poi(new PoiPK(12, 8), "Pub");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      poi = new Poi(new PoiPK(23, 6), "Supermercado");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      poi = new Poi(new PoiPK(28, 2), "Churrascaria");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      
      //testing no successfully insert - it has to return ResponseEnum.ALREADY_EXISTIS
      poi = new Poi(new PoiPK(19, 21), "Duplicated PK");
      poiJB = poiDAOI.insert(poi);
      assertEquals(ResponseEnum.ALREADY_EXISTIS, poiJB.getStatus());
      
      //testing throw exception - it has to return ResponseEnum.ERROR
      poiJB = poiDAOI.insert(null);
      assertEquals(ResponseEnum.ERROR, poiJB.getStatus());
      
   }

   
   /**
    * Test of listAll method, of class PoiDAO.
    */
   @Test
   @InSequence(2)
   public void testListAll() throws Exception {

      PoiJB poiJB = poiDAOI.listAll();
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

      Set<String> set = new HashSet<>(4);
      String coordinates = "";
      
      //it veirifies if all returned POIs are distincts and if them belongs to the map with the expected result
      for(PoiAuxJB poi: poiJB.getPois()){
         
         coordinates = poi.getX()+";"+poi.getY();
         assertEquals(true, set.add(coordinates));
         assertEquals(true, mapExpRes.containsKey(coordinates) && mapExpRes.get(coordinates).contentEquals(poi.getName()));
         
      }
      
      destroyData();
      poiJB = poiDAOI.listAll();
      assertEquals(ResponseEnum.NO_EXISTS, poiJB.getStatus());
      assertEquals(0, poiJB.getPois().size());
      
   }
   
   
   /**
    * Test of findByDistance method, of class PoiDAO.
    */
   @Test
   @InSequence(3)
   public void testFindByDistance() throws Exception {
      
      initializeData();
      
      PoiJB poiJB = poiDAOI.findByDistance(20, 10, 10);
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
      
      poiJB = poiDAOI.findByDistance(20, 10, 5);
      assertEquals(ResponseEnum.OK, poiJB.getStatus());
      assertEquals(1, poiJB.getPois().size());
      assertEquals(true, poiJB.getPois().get(0).getX() == 23 && poiJB.getPois().get(0).getY() == 6
         && poiJB.getPois().get(0).getName().contentEquals("Supermercado"));
      
      poiJB = poiDAOI.findByDistance(20, 10, 4);
      assertEquals(ResponseEnum.NO_EXISTS, poiJB.getStatus());
      assertEquals(0, poiJB.getPois().size());
      
      destroyData();
      poiJB = poiDAOI.findByDistance(20, 10, 4);
      assertEquals(ResponseEnum.NO_EXISTS, poiJB.getStatus());
      assertEquals(0, poiJB.getPois().size());
      initializeData();

   }
   
   
}