package com.xyinc.ejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heron Sanches
 */
@Entity
@NamedQueries({
   @NamedQuery(name = "Poi.findAll", query = "SELECT p FROM Poi p"),
   @NamedQuery(name = "Poi.findByDistance", query = "SELECT p FROM Poi p WHERE SQRT( (:idPoiNumX - p.poiPK.idPoiNumX)*(:idPoiNumX - p.poiPK.idPoiNumX) + (:idPoiNumY - p.poiPK.idPoiNumY)*(:idPoiNumY - p.poiPK.idPoiNumY) ) <= :distance")
})
public class Poi implements Serializable {
   
   @EmbeddedId
   protected PoiPK poiPK;

   @NotNull
   @Column(name = "txt_name")
   private String txtName;

   
   public Poi(){
   }
   
   
   public Poi(PoiPK poiPK, String txtName){
      
      this.poiPK = poiPK;
      this.txtName = txtName;
      
   }
   
   
   //getters and setters
   public PoiPK getPoiPK() {
      return poiPK;
   }
   
   public void setPoiPK(PoiPK poiPK) {
      this.poiPK = poiPK;
   }

   public String getTxtName() {
      return txtName;
   }

   public void setTxtName(String txtName) {
      this.txtName = txtName;
   } 
   

}