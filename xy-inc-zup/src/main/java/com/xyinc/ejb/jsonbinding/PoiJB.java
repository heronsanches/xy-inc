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

   
   public PoiJB() {
   }
 
   
   public PoiJB(ResponseEnum status, List<Poi> pois){
      
      this.status = status;
      this.pois = new ArrayList<>();
      
      for(Poi poi: pois)
         this.pois.add(new PoiAuxJB(poi.getPoiPK().getIdPoiNumX(), poi.getPoiPK().getIdPoiNumY(), poi.getTxtName()));
      
   }

   
   public void setStatus(ResponseEnum status) {
      this.status = status;
   }

   public void setPois(List<PoiAuxJB> pois) {
      this.pois = pois;
   }

   public ResponseEnum getStatus() {
      return status;
   }

   public List<PoiAuxJB> getPois() {
      return pois;
   }

   
}