package com.xyinc.zup.rest.application.em;

import com.xyinc.ejb.utils.ResponseEnum;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Default json binding message to return in cases of handling {@linkplain ExceptionMapper}
 * @author Heron Sanches
 */
public class MessageJB {
   
   private ResponseEnum status;
   private String message;

   
   public MessageJB(ResponseEnum status, String message) {
      
      this.status = status;
      this.message = message;
      
   }

   
   public ResponseEnum getStatus() {
      return status;
   }

   public String getMessage() {
      return message;
   }
  

}