/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Model;

/**
 *
 * @author ADMIN
 */
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "renters")
@XmlAccessorType(XmlAccessType.FIELD)
public class RenterXML {
    private ArrayList<Renter> renter;

    public ArrayList<Renter> getRenter() {
        return renter;
    }

    public void setRenter(ArrayList<Renter> renter) {
        this.renter = renter;
    }
}
