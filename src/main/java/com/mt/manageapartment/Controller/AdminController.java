/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Controller;


import com.mt.manageapartment.Dao.RoomDao;
import com.mt.manageapartment.Model.Renter;
import com.mt.manageapartment.Model.Room;
import com.mt.manageapartment.View.AdminView;
import com.mt.manageapartment.View.DetailView;
import com.mt.manageapartment.View.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.xml.bind.JAXBException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author ADMIN
 */
public class AdminController {
    private RoomDao roomDao;
    private AdminView adminView;
    private LoginView login;
    private DetailView detailView;
    //
    
    public AdminController(AdminView view){
        this.adminView = view;
        this.roomDao = new RoomDao();
        this.login = new LoginView();
        
        view.addAddRoomListener(new AddRoomListener());
        view.addDeleteListener(new DeleteRoomListener());
        view.addEditListener(new EditRoomListener());
        view.addSearchRoomListener(new SearchRoomListener());
        view.addClearListener(new ClearRoomListener());
        
        view.addListRoomSelectionListener(new ListRoomSelectionListener());
        view.addListServiceSelectionListener(new ListServiceSelectionListener());
        view.addListRenterSelectionListener(new ListRenterSelectionListener() );
        
        view.addSortListener(new SortListener());

        view.addLogoutListener(new LogoutListener());
        view.addEditServiceListener(new EditServiceListener());
        
        view.addDetailListener(new DetailListener());
        
        view.addAddRenterListener(new AddRenterListener());
        view.addSearchRenterListener(new SearchRenterListener());
        view.addDeleteRenterListener(new DeleteRenterListener());
        view.addEditRenterListener(new EditRenterListener());
        view.addClearRenterListener(new ClearRenterListener());
        view.addSortRenterListener(new SortRenterListener());

    }
    public void showRenterView() {
        ArrayList<Renter> renterList = roomDao.getRenterList();
        adminView.setVisible(true);
        adminView.showListRenter(renterList);
        adminView.clearRenterInfo();
        adminView.clearServiceInfo();
    }
    public void showRoomview(){
        ArrayList<Room> roomList = roomDao.getRoomList();
        adminView.setVisible(true);
        adminView.showListRoom(roomList);
        adminView.showListService(roomList);
        adminView.clearServiceInfo();
        adminView.clearRenterInfo();
    }
    
    
//    class Reset1Listener implements ActionListener{
//        public void actionPerformed(ActionEvent e) {           
//            ArrayList<Renter> renterList = roomDao.getRenterList();
//            adminView.showListRenter(roomDao.getRenterList());
//            adminView.clearRenterInfo();
//        }
//    }
    class ListRoomSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            adminView.fillRoomFromSelectedRow();
        }
    }
    class ListServiceSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            adminView.fillServiceFromSelectedRow();
        }
    }
    class ListRenterSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            adminView.fillRenterFromSelectedRow();
        }
    }
    class AddRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = adminView.getRoomInfor();
            room.setMaPhong((new Random().nextInt(89999)+100000)+ "");
            if (room != null) {
                int found = roomDao.add(room);
                if(found == 0){
                    adminView.showMessage("Phòng số "+ room.getSoPhong() +" của "+room.getHoTen()+" đã tồn tại!");
                }
                if(found == 1){
                    adminView.showRoom(room);
                    adminView.showListRoom(roomDao.getRoomList());
                    adminView.showMessage("Thêm phòng của "+room.getHoTen()+" thành công!");
                    adminView.clearRoomInfo();
                }
                
            }

        }
    }
    class AddRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Renter renter = adminView.getRenterInfor();
            String soPhongRenter = adminView.getSoPhongRenter();
            
            if(renter != null){
                int found = roomDao.addRenter(renter);
                if(found == 0){
                    adminView.showMessage("Phòng số "+ soPhongRenter+" không cho thuê hoặc trùng với người đã thuê!");
                } 
                if(found == 1){
                    adminView.showRenter(renter);
                    adminView.showListRenter(roomDao.getRenterList());
                    adminView.showMessage("Thêm người thuê "+renter.getHoTen()+" thành công!");
                    adminView.clearRenterInfo();
                }
                showRoomview();
                showRenterView();
            }
            
        }
    }
    class ClearRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.clearRoomInfo();
            showRoomview();
            showRenterView();
        }
    }
    class ClearRenterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            adminView.clearRenterInfo();
            showRoomview();
            showRenterView();
        }
    }
    class DeleteRoomListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Room room = adminView.getRoomInfor();
            if (room != null ) {
                
                ArrayList<Renter> foundRenter = roomDao.searchbyRenterID(room.getSoPhong());
                if (foundRenter != null) {
                    for (Renter renter : foundRenter) {
                        if (roomDao.deleteRenter(renter)) {
                            adminView.clearRenterInfo();
                            //adminView.showListRenter(roomDao.getRenterList());
                            //adminView.showMessage("Xóa người thuê " + renter.getHoTen() + " thành công!");
                        }
                    }
                }
                
                roomDao.delete(room);           
                adminView.clearRoomInfo();
                adminView.showListRoom(roomDao.getRoomList());
                adminView.showMessage("Xóa phòng của "+room.getHoTen()+" thành công!");
                showRoomview();
                showRenterView();
            }
            showRoomview();
            showRenterView();           
        }
    }
    //Renter
    class DeleteRenterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Renter renter = adminView.getRenterInfor();
            if (renter != null) {
                if(roomDao.deleteRenter(renter)){
                    adminView.clearRenterInfo();
                    adminView.showListRenter(roomDao.getRenterList());
                    adminView.showMessage("Xóa người thuê "+renter.getHoTen()+" thành công!");
                }
                showRoomview();
                showRenterView();
            }
        }
    }        
    
    class EditRoomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = adminView.getRoomInfor();
            if (room != null) {
                int found = roomDao.edit(room);
                if(found == 0){
                    adminView.showRoom(room);
                    adminView.showListRoom(roomDao.getRoomList());
                    adminView.showMessage("Cập nhật thông tin cho phòng của "+room.getHoTen()+" thành công!");
                }
                if(found == 1){
                    adminView.showMessage("Số CMND đã tồn tại");
                }
                showRoomview();
                showRenterView();
            }
        }
    }
    //Renter
    class EditRenterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Renter renter = adminView.getRenterInfor();
            if (renter != null) {
                roomDao.editRenter(renter);
                adminView.showRenter(renter);
                adminView.showListRenter(roomDao.getRenterList());
                adminView.showMessage("Cập nhật thông tin cho người thuê "+renter.getHoTen()+" thành công!");
                showRoomview();
                showRenterView();
            }
        }
    }        
//    EditServiceListener
    class EditServiceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Room room = adminView.getServiceInfor();
            if (room != null) {
                roomDao.editService(room);
                adminView.showService(room);
                adminView.showListService(roomDao.getRoomList());
                adminView.showMessage("Cập nhật thông tin cho dịch vụ của "+room.getHoTen()+" thành công!");
                //adminView.showMessage("Cập nhật thông tin cho dịch vụ của phòng "+room.getHoTen()+" thành công!");
                showRoomview();
                showRenterView();
            }
        }
    }
    class SearchRoomListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            adminView.clearRoomInfo();
            ArrayList<Room> foundRoom = new ArrayList<>();
            String roomname = adminView.getRoomName();
            String roomID = adminView.getRoomID();
            if(roomname != null && !roomname.equals("")){
                foundRoom = roomDao.searchRoom(roomname);
                if(foundRoom == null){
                    adminView.showMessage("Không tìm thấy phòng của "+roomname);
                }else{
                    adminView.showListRoom(foundRoom);
                    
                }
            }else{
                foundRoom = roomDao.searchbyRoomID(roomID);
                if(foundRoom == null){
                    adminView.showMessage("Không tìm thấy phòng có mã: "+roomID);
                }else{
                    adminView.showListRoom(foundRoom);
                }
            }
            
        }
    }
    // search Renter
    class SearchRenterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            adminView.clearRenterInfo();
            ArrayList<Renter> foundRenter = new ArrayList<>();
            String rentername = adminView.getRenterName();
            String renterID = adminView.getRenterID();
            if(rentername != null && !rentername.equals("")){
                foundRenter = roomDao.searchRenter(rentername);
                if(foundRenter == null){
                    adminView.showMessage("Không tìm thấy người thuê: "+rentername);
                }else{
                    adminView.showListRenter(foundRenter);
                    
                }
            }else{
                foundRenter = roomDao.searchbyRenterID(renterID);
                if(foundRenter == null){
                    adminView.showMessage("Không tìm thấy phòng số: "+renterID);
                }else{
                    adminView.showListRenter(foundRenter);
                }
            }
            
        }
    }        
    
    class SortListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String choice = adminView.getChoice();
            if(!choice.equals("") || choice != null){
                if(choice.equals("Tên từ A - Z")){
                    roomDao.sortAZ();
                    adminView.showListRoom(roomDao.getRoomList());                
                }
                if(choice.equals("Tên từ Z - A")){
                    roomDao.sortZA();
                    adminView.showListRoom(roomDao.getRoomList());                
                }
                if(choice.equals("Trả phí nhiều nhất")){
                    roomDao.sortMax();
                    adminView.showListRoom(roomDao.getRoomList());                
                }
                if(choice.equals("Trả phí ít nhất")){
                    roomDao.sortMin();
                    adminView.showListRoom(roomDao.getRoomList());                
                }
                showRoomview();
                showRenterView();
            }
        }
    }
    // Sort Renter
    class SortRenterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String choice = adminView.getChoiceRenter();
            if(!choice.equals("") || choice != null){
                if(choice.equals("Tên từ A - Z")){
                    roomDao.sortAZRenter();
                    adminView.showListRenter(roomDao.getRenterList());                
                }
                if(choice.equals("Tên từ Z - A")){
                    roomDao.sortZARenter();
                    adminView.showListRenter(roomDao.getRenterList());                
                }                
            showRoomview();
            showRenterView();
            }
        }
    }        
    class LogoutListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
//            adminView.setVisible(false);
//            login.setVisible(true);
              System.exit(0);
        }
    }
    public class ExcelPrinter {
    public static void printTableToExcel(JTable table, String fileName) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        
        Row row = sheet.createRow(3);
        Row headerRow = sheet.createRow(0);
        for (int j = 0; j < table.getColumnCount(); j++) {
            Cell cell = headerRow.createCell(j);
            cell.setCellValue(table.getColumnName(j));
        }
        
        for (int i = 0; i < table.getRowCount(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < table.getColumnCount(); j++) {
                Object value = table.getValueAt(i, j);
                if (value != null) {
                    row.createCell(j).setCellValue(value.toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        }
    }
}
    class DetailListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            detailView = new DetailView();
            DetailController detailController = new DetailController(detailView);
            detailController.show();            
        }
    }



}
