package com.xyinc.ejb.sb.dao;

import com.xyinc.ejb.entity.Poi;
import com.xyinc.ejb.jsonbinding.PoiJB;
import javax.ejb.Local;

/**
 *
 * @author Heron Sanches
 */
@Local
public interface PoiDAOI {
   
   PoiJB insert(Poi poi);
   PoiJB listAll();
   PoiJB findByDistance(int idPoiNumX, int idPoiNumY, int distance);
   
}