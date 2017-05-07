package com.xyinc.ejb.sb.stateless;

import com.xyinc.ejb.jsonbinding.PoiJB;
import javax.ejb.Local;

/**
 *
 * @author Heron Sanches
 */
@Local
public interface PoisSBI {
   
   PoiJB insert(int idPoiNumX, int idPoiNumY, String name);
   PoiJB listAll();
   PoiJB findByDistance(int idPoiNumX, int idPoiNumY, int distance);
   
}