package com.xyinc.ejb.jsonbinding;

/**
 *
 * @author Heron Sanches
 */
public class PoiAuxJB {

   private int x;
   private int y;
   private String name;

   
   public PoiAuxJB() {
   }

      
   public PoiAuxJB(int x, int y, String name){
      
      this.x = x;
      this.y = y;
      this.name = name;
      
   }

   
   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }

   public String getName() {
      return name;
   }
   
   
}