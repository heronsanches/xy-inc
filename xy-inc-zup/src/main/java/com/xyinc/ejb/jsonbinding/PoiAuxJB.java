package com.xyinc.ejb.jsonbinding;

/**
 *
 * @author Heron Sanches
 */
public class PoiAuxJB {

   private int x;
   private int y;
   private String name;

   
   public PoiAuxJB(int x, int y, String name){
      
      this.x = x;
      this.y = y;
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