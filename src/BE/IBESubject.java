/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import TableModels.ITableObserver;

import java.util.ArrayList;


/**
 *
 * @author Jakob
 */
public abstract class IBESubject {
     private ArrayList<ITableObserver> observers 
      = new ArrayList<>();

    
       public void addObserver(ITableObserver observer){
      observers.add(observer);		
   }
       public void remove(ITableObserver obersver){
           observers.remove(obersver);
       }

   public void notifyAllObservers(){
      for (ITableObserver observer : observers) {
         observer.update();
      }
}}
