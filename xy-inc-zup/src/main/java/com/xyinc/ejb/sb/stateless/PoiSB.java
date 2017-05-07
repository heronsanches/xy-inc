package com.xyinc.ejb.sb.stateless;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.entity.PoiPK;
import com.xyinc.ejb.jsonbinding.PoiJB;
import com.xyinc.ejb.sb.dao.PoiDAOI;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Heron Sanches
 */
@Stateless
public class PoiSB implements PoisSBI{
   
   @EJB
   private PoiDAOI poiDAOI;

   
   @Override
   public PoiJB insert(int idPoiNumX, int idPoiNumY, String txtName) {
      return poiDAOI.insert(new Poi(new PoiPK(idPoiNumX, idPoiNumY), txtName));
   }
   

   @Override
   public PoiJB listAll() {
      return poiDAOI.listAll();
   }
   

   @Override
   public PoiJB findByDistance(int idPoiNumX, int idPoiNumY, int distance) {
      return poiDAOI.findByDistance(idPoiNumX, idPoiNumY, distance);
   }
   
   
}