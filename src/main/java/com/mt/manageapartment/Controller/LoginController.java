/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Controller;

import com.mt.manageapartment.Dao.UserDao;
import com.mt.manageapartment.Model.User;
import com.mt.manageapartment.View.AdminView;
import com.mt.manageapartment.View.LoginView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author ADMIN
 */
public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private AdminView adminView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.adminView = new AdminView();
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
        
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
       
    }
        
    class LoginListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                //loginView.showMessage("Đăng nhập thành công!");
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                adminView.setVisible(true);
                AdminController adminController = new AdminController(adminView);
                adminController.showRoomview();
                adminController.showRenterView();
                
                loginView.setVisible(false);
              
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
        
    }
}
