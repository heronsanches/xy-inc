package com.xyinc.ejb.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Heron Sanches
 */
@Embeddable
public class PoiPK implements Serializable {

   @NotNull
   @Column(name = "id_poi_num_x")
   private Integer idPoiNumX;
   
   @NotNull
   @Column(name = "id_poi_num_y")
   private Integer idPoiNumY;

   
   public PoiPK() {
   }

   
   public PoiPK(Integer idPoiNumX, Integer idPoiNumY){
      
      this.idPoiNumX = idPoiNumX;
      this.idPoiNumY = idPoiNumY;
      
   }
   
   
   public Integer getIdPoiNumX() {
      return idPoiNumX;
   }

   public void setIdPoiNumX(Integer idPoiNumX) {
      this.idPoiNumX = idPoiNumX;
   }

   public Integer getIdPoiNumY() {
      return idPoiNumY;
   }

   public void setIdPoiNumY(Integer idPoiNumY) {
      this.idPoiNumY = idPoiNumY;
   }

   
}