/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mt.manageapartment.Model;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author ADMIN
 */
public class Room {
    private String maPhong, soPhong, hoTen, cmnd, diaChi, gioiTinh;
    private String sdt;
    private String ngayDangKi, ngayHH;
    private String tinhTrang, tinhTrangO;
    private int quanLy, dien, nuoc, baoDuong, veSinh, trongXe, tongPhi;
    private ArrayList<Renter> renters; 

    public Room(String maPhong, String soPhong,String hoTen, String cmnd, String diaChi, String gioiTinh, String sdt, 
            String ngayDangKi, String ngayHH, int quanLy, int dien, int nuoc, 
            int baoDuong, int veSinh, int trongXe, int tongPhi, String tinhTrang, String tinhTrangO,
            ArrayList<Renter> renters) {
        this.maPhong = maPhong;
        this.soPhong = soPhong;
        this.hoTen = hoTen;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngayDangKi = ngayDangKi;
        this.ngayHH = ngayHH;
        this.quanLy = quanLy;
        this.baoDuong = baoDuong;
        this.dien = dien;
        this.nuoc = nuoc;
        this.veSinh = veSinh;
        this.trongXe = trongXe;
        this.tinhTrang = tinhTrang;
        this.tinhTrangO = tinhTrangO;
        this.tongPhi = tongPhi;
        this.renters = renters;
    }

    public Room() {
    }

    public ArrayList<Renter> getRenters() {
        return renters;
    }

    public void setRenters(ArrayList<Renter> renters) {
        this.renters = renters;
    }

    
    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }
    
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayDangKi() {
        return ngayDangKi;
    }

    public void setNgayDangKi(String ngayDangKi) {
        this.ngayDangKi = ngayDangKi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getQuanLy() {
        return quanLy;
    }

    public void setQuanLy(int quanLy) {
        this.quanLy = quanLy;
    }

    public int getDien() {
        return dien;
    }

    public void setDien(int dien) {
        this.dien = dien;
    }

    public int getNuoc() {
        return nuoc;
    }

    public void setNuoc(int nuoc) {
        this.nuoc = nuoc;
    }

    public int getBaoDuong() {
        return baoDuong;
    }

    public void setBaoDuong(int baoDuong) {
        this.baoDuong = baoDuong;
    }

    public int getVeSinh() {
        return veSinh;
    }

    public void setVeSinh(int veSinh) {
        this.veSinh = veSinh;
    }

    public int getTrongXe() {
        return trongXe;
    }

    public void setTrongXe(int trongXe) {
        this.trongXe = trongXe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTinhTrangO() {
        return tinhTrangO;
    }

    public void setTinhTrangO(String tinhTrangO) {
        this.tinhTrangO = tinhTrangO;
    }

    
    public int getTongPhi() {
        return quanLy + dien + nuoc + veSinh + baoDuong+ trongXe;
    }

    public void setTongPhi(int tongPhi) {
        this.tongPhi = tongPhi;
    }

    public String getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(String ngayHH) {
        this.ngayHH = ngayHH;
    }
    
    
}
