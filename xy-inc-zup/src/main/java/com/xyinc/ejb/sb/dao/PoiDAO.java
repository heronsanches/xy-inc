package com.xyinc.ejb.sb.dao;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.jsonbinding.PoiJB;
import com.xyinc.ejb.utils.PersistenceContextConstants;
import com.xyinc.ejb.utils.ResponseEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 *
 * @author Heron Sanches
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PoiDAO implements PoiDAOI{

   @PersistenceContext(unitName = PersistenceContextConstants.XYINC_POI)
   private EntityManager em;
           
   @Resource
   private UserTransaction ut;
   
   
   @Override
   public PoiJB insert(Poi poi) {
      
      List<Poi> pois = new ArrayList<>();
      
      try {

        Poi p = em.find(Poi.class, poi.getPoiPK());
        pois.add(poi);
         
         if(p != null)
            return new PoiJB(ResponseEnum.ALREADY_EXISTIS, pois);
         
         ut.begin();
         em.persist(poi);
         ut.commit();
         return new PoiJB(ResponseEnum.OK, pois);
      
      } catch (Exception ex) {
         
         Logger.getLogger(PoiDAO.class.getName()).log(Level.SEVERE, null, ex);
         
         try {
            ut.rollback();
         } catch (IllegalStateException | SecurityException | SystemException ex1) {
            Logger.getLogger(PoiDAO.class.getName()).log(Level.SEVERE, null, ex1);
         }
      
      }
         
      return new PoiJB(ResponseEnum.ERROR, pois);
      
   }

   
   @Override
   public PoiJB listAll() {
      
      List<Poi> pois = null;
      
      try {
         
         pois = em.createNamedQuery("Poi.findAll", Poi.class).getResultList();
         
         if(pois.size() > 0)
            return new PoiJB(ResponseEnum.OK, pois);
         
         return new PoiJB(ResponseEnum.NO_EXISTS, pois);
         
      } catch (Exception e) {
         
         Logger.getLogger(PoiDAO.class.getName()).log(Level.SEVERE, null, e);
         return new PoiJB(ResponseEnum.ERROR, pois);
         
      }
              
   }

   
   @Override
   public PoiJB findByDistance(int idPoiNumX, int idPoiNumY, int distance) {
      
      List<Poi> pois = null;
      
      try {
         
         pois = em.createNamedQuery("Poi.findByDistance", Poi.class)
                 .setParameter("idPoiNumX", idPoiNumX)
                 .setParameter("idPoiNumY", idPoiNumY)
                 .setParameter("distance", Double.valueOf(String.valueOf(distance))).getResultList();
         
         if(pois.size() > 0)
            return new PoiJB(ResponseEnum.OK, pois);
         
         return new PoiJB(ResponseEnum.NO_EXISTS, pois);
         
      } catch (Exception e) {
         
         Logger.getLogger(PoiDAO.class.getName()).log(Level.SEVERE, null, e);
         return new PoiJB(ResponseEnum.ERROR, pois);
         
      }
      
   }

   
}