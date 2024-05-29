/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//file chuẩn
package com.mt.manageapartment;

import com.mt.manageapartment.Controller.LoginController;
import com.mt.manageapartment.View.LoginView;
import java.awt.EventQueue;

/**
 *
 * @author ADMIN
 */
public class ManageApartment {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController loginController = new LoginController(view);
                // hiển thị màn hình login
                loginController.showLoginView();
            }
        });
    }
}
