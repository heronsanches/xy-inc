package com.xyinc.ejb.jsonbinding;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.utils.ResponseEnum;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Heron Sanches
 */
public class PoiJB {

   private ResponseEnum status;
   private List<PoiAuxJB> pois;
   
   
   public PoiJB(ResponseEnum status, List<Poi> pois){
      
      this.status = status;
      this.pois = new ArrayList<>();
      
      for(Poi poi: pois)
         this.pois.add(new PoiAuxJB(poi.getPoiPK().getIdPoiNumX(), poi.getPoiPK().getIdPoiNumY(), poi.getTxtName()));
      
   }

   
   public ResponseEnum getStatus() {
      return status;
   }

   public List<PoiAuxJB> getPois() {
      return pois;
   }

   
}