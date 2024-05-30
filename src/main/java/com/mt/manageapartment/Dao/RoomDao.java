/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Dao;

import com.mt.manageapartment.Model.Renter;
import com.mt.manageapartment.Model.RenterXML;
import com.mt.manageapartment.Model.Room;
import com.mt.manageapartment.Model.RoomXML;
import com.mt.manageapartment.Utils.FileUtils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ADMIN
 */
public class RoomDao {
    
    private static final String ROOM_FILE_NAME = "src\\main\\resources\\xml\\room.xml";
    private static final String RENTER_FILE_NAME = "src\\main\\resources\\xml\\renter.xml";
    private ArrayList<Room> roomList;
    private ArrayList<Renter> renterList;
    
    public RoomDao(){
        this.roomList = readListRooms();
        this.renterList = readListRenters();
        if(roomList == null){
            roomList = new ArrayList<Room>();
        }
        if (renterList == null) {
            renterList = new ArrayList<Renter>();
        }
    }

    public ArrayList<Renter> getRenterList() {
        return renterList;
    }

    public void setRenterList(ArrayList<Renter> renterList) {
        this.renterList = renterList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }
    
//    /**
//     * Lưu các đối tượng student vào file student.xml
//     * 
//     * @param students
//     */  
    public void writeListRooms(ArrayList<Room> rooms){
        RoomXML roomXML = new RoomXML();
        roomXML.setRoom(rooms);
        FileUtils.writeXMLtoFile(ROOM_FILE_NAME, roomXML);
    }
//    /**
//     * Đọc các đối tượng student từ file student.xml
//     * 
//     * @return list student
//     */
    public ArrayList<Room> readListRooms(){
        ArrayList<Room> list = new ArrayList<Room>();
        RoomXML roomXML = (RoomXML) FileUtils.readXMLFile(
                ROOM_FILE_NAME, RoomXML.class);
        if (roomXML != null) {
            list = roomXML.getRoom();
        }
        return list;
    }
    
    //
    public void writeListRenters(ArrayList<Renter> renters) {
        RenterXML renterXML = new RenterXML();
        renterXML.setRenter(renters);
        FileUtils.writeXMLtoFile(RENTER_FILE_NAME, renterXML);
    }
    //
    public ArrayList<Renter> readListRenters() {
        ArrayList<Renter> list = new ArrayList<Renter>();
        RenterXML renterXML = (RenterXML) FileUtils.readXMLFile(
                RENTER_FILE_NAME, RenterXML.class);
        if (renterXML != null) {
            list = renterXML.getRenter();
        }
        return list;
    }
    //
     /**
     * thêm student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public int add(Room room) {
        boolean isFound = false;
        for(int i = 0; i < roomList.size(); i++){
            if(roomList.get(i).getSoPhong().equals(room.getSoPhong())){
                isFound = true;
            }
        }
        if(isFound){
            return 0;
        }else{
            roomList.add(room);
            writeListRooms(roomList);
            return 1;
        }
        
    }
    
    //add Service
    public int addRenter(Renter renter) {
        boolean isFound1 = false;
        boolean isFound2 = false;
        int vitri = 0;
        int size = roomList.size();
        for(int i = 0; i < size; i++){
            if(roomList.get(i).getSoPhong().equals(renter.getSoPhong()) 
                    && roomList.get(i).getTinhTrangO().equals("Chủ nhà cho thuê") )
            {
                if(roomList.get(i).getRenters() == null){ 
                    isFound2 = false;
                } else{
                    for(int j = 0; j < roomList.get(i).getRenters().size(); j++){
                        if(roomList.get(i).getRenters().get(j).getCmnd().equals(renter.getCmnd())){
                            isFound2 = true;
                        }
                    }
                }
                isFound1 = true;
                vitri = i;
            }
        }
        if (isFound2 == false && isFound1 == true){
            for(int i = 0; i < size; i++){
                if(i == vitri){
                    ArrayList<Renter> renters = roomList.get(vitri).getRenters();
                    if (renters == null) {
                        renters = new ArrayList<Renter>();
                    }
                    renters.add(renter);
                    
                    renterList.add(renter);
                    writeListRenters(renterList);
                    
                    roomList.get(vitri).setRenters(renters);
                    //isFound1 = true;               
                }  
            }                
            writeListRooms(roomList);
            return 1;
        } else{
            return 0;
        }
        
    }
    public int edit(Room room) {
        int size = roomList.size();
        boolean checkTrung = false;
        for (int i = 0; i < size; i++) {

                if (roomList.get(i).getMaPhong().equals(room.getMaPhong())) {
                    roomList.get(i).setHoTen(room.getHoTen());
                    roomList.get(i).setMaPhong(room.getMaPhong());
                    roomList.get(i).setCmnd(room.getCmnd());
                    roomList.get(i).setDiaChi(room.getDiaChi());
                    roomList.get(i).setGioiTinh(room.getGioiTinh());
                    roomList.get(i).setNgayDangKi(room.getNgayDangKi());
                    roomList.get(i).setNgayHH(room.getNgayHH());
                    roomList.get(i).setSdt(room.getSdt());
                    roomList.get(i).setTinhTrangO(room.getTinhTrangO());

                    writeListRooms(roomList);
                    break;
                }
        }
        if(checkTrung) return 1;
        return 0;
    }
    
    public int editRenter(Renter renter) {
        int size = renterList.size();
        int size2 = roomList.size();
        boolean check = false;
        for(int i = 0; i < size2; i++){
            if(roomList.get(i).getRenters() != null){
                for(int j = 0; j < roomList.get(i).getRenters().size(); j++){
                        if(roomList.get(i).getRenters().get(j).getCmnd().equals(renter.getCmnd())){
                            roomList.get(i).getRenters().get(j).setCmnd(renter.getCmnd());
                            roomList.get(i).getRenters().get(j).setHoTen(renter.getHoTen());
                            roomList.get(i).getRenters().get(j).setDiaChi(renter.getDiaChi());
                            roomList.get(i).getRenters().get(j).setGioiTinh(renter.getGioiTinh());
                            roomList.get(i).getRenters().get(j).setNgayDangKi(renter.getNgayDangKi());
                            roomList.get(i).getRenters().get(j).setNgayHH(renter.getNgayHH());
                            roomList.get(i).getRenters().get(j).setSdt(renter.getSdt());

                            writeListRooms(roomList);
                            
                            for (int k = 0; k < size; k++) {
                                if (renterList.get(k).getCmnd().equals(renter.getCmnd())) {
                                    renterList.get(k).setHoTen(renter.getHoTen());
                                    renterList.get(k).setCmnd(renter.getCmnd());
                                    renterList.get(k).setDiaChi(renter.getDiaChi());
                                    renterList.get(k).setGioiTinh(renter.getGioiTinh());
                                    renterList.get(k).setNgayDangKi(renter.getNgayDangKi());
                                    renterList.get(k).setNgayHH(renter.getNgayHH());
                                    renterList.get(k).setSdt(renter.getSdt());

                                    writeListRenters(renterList);                                  
                                }
                            }
                            break;
                        }
                }
                check = true;
            }
        }       
        if(check == true) return 1;
        return 0;
    }
    
    public void editService(Room room) {
        int size = roomList.size();
        for (int i = 0; i < size; i++) {
            if (roomList.get(i).getMaPhong().equals(room.getMaPhong())) {
                roomList.get(i).setQuanLy(room.getQuanLy());
                roomList.get(i).setBaoDuong(room.getBaoDuong());
                roomList.get(i).setDien(room.getDien());
                roomList.get(i).setNuoc(room.getNuoc());
                roomList.get(i).setVeSinh(room.getVeSinh());
                roomList.get(i).setTrongXe(room.getTrongXe());
                
                roomList.get(i).setTinhTrang(room.getTinhTrang());
                
                writeListRooms(roomList);
                break;
            }
        }
    }
    public boolean delete(Room room) {
        boolean isFound = false;
        int size = roomList.size();
        int size2 = renterList.size();
        for (int i = 0; i < size; i++) {
            if (roomList.get(i).getMaPhong().equals(room.getMaPhong())) {
                room = roomList.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            roomList.remove(room);
            
            writeListRooms(roomList);
            return true;
        }
        return false;
    }
    
    //Delete service
    public boolean deleteRenter(Renter renter) {
        boolean isFound = false;
        boolean isFoundInRoom = false;
        int size = renterList.size();
        for (int i = 0; i < size; i++) {
            if (renterList.get(i).getCmnd().equals(renter.getCmnd())) {
                renter = renterList.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            renterList.remove(renter);
            writeListRenters(renterList);
            int size2 = roomList.size();
            for(int i = 0; i < size2; i++){

                if(roomList.get(i).getSoPhong().equals(renter.getSoPhong())){
                    ArrayList<Renter> renters = roomList.get(i).getRenters();
                    for(Renter r : renters){
                        if(r.getCmnd().equals(renter.getCmnd())){
                            renters.remove(r);
                            roomList.get(i).setRenters(renters);
                            isFoundInRoom = true;
                            break;
                        }
                    }
                }
            }
            if(isFoundInRoom){
                writeListRooms(roomList);
                return true;
            }
        }
        return false;
    }
    //search Room
    public ArrayList<Room> searchRoom(String roomname){
        boolean isFound = false;
        ArrayList<Room> foundRoom = new ArrayList<>();
        Room found = null;
        int size = roomList.size();
        String keyword = roomname.toLowerCase().replaceAll("\\s+","");
        for(int i = 0; i < size ; i++){
            String name = roomList.get(i).getHoTen().toLowerCase().replaceAll("\\s+","");
            if (name.contains(keyword)) {
                found = roomList.get(i);
                foundRoom.add(found);
                isFound = true;
            }
        }
        if(isFound){
            return foundRoom;
        }else{
            return null;
        }
       
    }
    //search by Room ID
    public ArrayList<Room> searchbyRoomID(String roomID){
        boolean isFound = false;
        ArrayList<Room> foundRoom = new ArrayList<>();
        Room found = null;
        int size = roomList.size();
        String keyword = roomID.toLowerCase().replaceAll("\\s+","");
        for(int i = 0; i < size ; i++){
            String ID = roomList.get(i).getMaPhong().toLowerCase().replaceAll("\\s+","");
            if (ID.contains(keyword)) {
                found = roomList.get(i);
                foundRoom.add(found);
                isFound = true;
            }
        }
        if(isFound){
            return foundRoom;
        }else{
            return null;
        }
       
    }
    //search Renter
    public ArrayList<Renter> searchRenter(String rentername){
        boolean isFound = false;
        ArrayList<Renter> foundRenter = new ArrayList<>();
        Renter found = null;
        int size = renterList.size();
        String keyword = rentername.toLowerCase().replaceAll("\\s+","");
        for(int i = 0; i < size ; i++){
            String name = renterList.get(i).getHoTen().toLowerCase().replaceAll("\\s+","");
            if (name.contains(keyword)) {
                found = renterList.get(i);
                foundRenter.add(found);
                isFound = true;
            }
        }
        if(isFound){
            return foundRenter;
        }else{
            return null;
        }
       
    }
    //search by So phong  Renter
    public ArrayList<Renter> searchbyRenterID(String soPhong){
        boolean isFound = false;
        ArrayList<Renter> foundRenter = new ArrayList<>();
        Renter found = null;
        int size = renterList.size();
        String keyword = soPhong.toLowerCase().replaceAll("\\s+","");
        for(int i = 0; i < size ; i++){
            String ID = renterList.get(i).getSoPhong().toLowerCase().replaceAll("\\s+","");
            if (ID.contains(keyword)) {
                found = renterList.get(i);
                foundRenter.add(found);
                isFound = true;
            }
        }
        if(isFound){
            return foundRenter;
        }else{
            return null;
        }
       
    }
    public void sortZA() {
    Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    Collections.sort(roomList, new Comparator<Room>() {
        @Override
        public int compare(Room room1, Room room2) {
            return collator.compare(room2.getHoTen(), room1.getHoTen());
        }
    });
    }
    
    public void sortAZ() {
    Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    Collections.sort(roomList, new Comparator<Room>() {
        
        public int compare(Room room1, Room room2) {
            return collator.compare(room1.getHoTen(), room2.getHoTen());
        }
    });
    }
    
    
    public void sortZARenter() {
    Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    Collections.sort(renterList, new Comparator<Renter>() {
        @Override
        public int compare(Renter renter1, Renter renter2) {
            return collator.compare(renter2.getHoTen(), renter1.getHoTen());
        }
    });
    }
    public void sortAZRenter() {
    Collator collator = Collator.getInstance(new Locale("vi", "VN"));
    Collections.sort(renterList, new Comparator<Renter>() {
        
        public int compare(Renter renter1, Renter renter2) {
            return collator.compare(renter1.getHoTen(), renter2.getHoTen());
        }
    });
    }
    public void sortMax(){
        Collections.sort(roomList, new Comparator<Room>() {
            public int compare(Room room1, Room room2) {
                if (room1.getTongPhi()< room2.getTongPhi()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    public void sortMin(){
        Collections.sort(roomList, new Comparator<Room>() {
            public int compare(Room room1, Room room2) {
                if (room1.getTongPhi()> room2.getTongPhi()) {
                    return 1;
                }
                return -1;
            }
        });
    }
    
}
