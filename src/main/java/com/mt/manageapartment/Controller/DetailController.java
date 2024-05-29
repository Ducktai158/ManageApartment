/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Controller;

import com.mt.manageapartment.Dao.RoomDao;
import com.mt.manageapartment.View.DetailView;

/**
 *
 * @author ADMIN
 */
public class DetailController {
    private RoomDao roomDao;
    private DetailView detailView;
    public DetailController(DetailView view){
         this.detailView = view;
         this.roomDao = new RoomDao();
     }
     public void show(){
         detailView.setVisible(true);
         detailView.loadDetail();
     }
}
