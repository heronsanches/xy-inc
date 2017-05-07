package com.xyinc.zup.rest.application.poi.resources.jsonbinding.poi;

import com.xyinc.zup.rest.application.poi.resources.PoiR;

/**
 * (JBI - Json Binding Input) for the method insert of the {@linkplain PoiR} resource
 * @author Heron Sanches
 */
public class InsertJBI {
   
   private String name;
   private int x;
   private int y;

   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }
  

}