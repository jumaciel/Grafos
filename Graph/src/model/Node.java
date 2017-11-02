/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


public class Node {
        
   public String id;
   private int degree;
   
   public Node() {
        
   }

   public String getId() {
       return id;
   }

   public void setId(String id) {
       this.id = id;
   }

    public int getGrau() {
        return degree;
    }

    public void setGrau(int grau) {
        this.degree = grau;
    }
    
}
