/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynh;

/**
 *
 * @author Admin
 */
public class MayTinh {

    private int maMayTinh;
    private String tenMayTinh;
    private String mauSac;
    private double giaBan;

    public MayTinh() {
    }

    public MayTinh(MayTinh mt) {
        this.maMayTinh = mt.getMaMayTinh();
        this.tenMayTinh = mt.getTenMayTinh();
        this.mauSac = mt.getMauSac();
        this.giaBan = mt.getGiaBan();
    }

    public MayTinh(int maMayTinh, String tenMayTinh, String mauSac, double giaBan) {
        this.maMayTinh = maMayTinh;
        this.tenMayTinh = tenMayTinh;
        this.mauSac = mauSac;
        this.giaBan = giaBan;
    }

    public int getMaMayTinh() {
        return maMayTinh;
    }

    public void setMaMayTinh(int maMayTinh) {
        this.maMayTinh = maMayTinh;
    }

    public String getTenMayTinh() {
        return tenMayTinh;
    }

    public void setTenMayTinh(String tenMayTinh) {
        this.tenMayTinh = tenMayTinh;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

}
