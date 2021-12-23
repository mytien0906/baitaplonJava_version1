/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
import huynh.MayTinh;
import huynh.MayTinhModel;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestQuanLyMayTinh {
    /*
    1. Lay ra danh sach may tinh
     */
    @Test
    public void testLayDanhSachMayTinh(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        qlmt.themMayTinh(mt1);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        assertEquals(expect.size(), actual.size());
    }
    /*
    2. Them thong tin 1 may tinh
     */
    @Test
    public void testThemMayTinh(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "asus 2", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "asus 3", "mau vand", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        expect.add(mt2);
        expect.add(mt3);
        assertEquals(expect.size(), actual.size());
    }
    /*
    4. Sua thong tin mot may tinh
     */
    @Test
    public void testSuaThongTinMayTinh(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        MayTinh mt2 = new MayTinh(1, "asus", "mau trang", 800);
        qlmt.themMayTinh(mt1);
        qlmt.suaThongTinMayTinh(mt2);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        assertEquals(expect.get(0).getTenMayTinh().toString(), actual.get(0).getTenMayTinh().toString());
    }
    /*
    3. Xoa 1 may tinh ra khoi danh sach
     */
    @Test
    public void testXoaMayTinh(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "asus 2", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "asus 3", "mau vand", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        qlmt.xoaMayTinh(mt1);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        expect.add(mt2);
        expect.add(mt3);
        expect.remove(mt3);
        assertEquals(expect.size(), actual.size());
    }
    @Test
    public void testSapXepTheoGiaBan(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "asus 2", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "asus 3", "mau vand", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        expect.add(mt2);
        expect.add(mt3);
        assertEquals(expect, actual);
    }
    @Test
    public void testSapXepTheoTenVaGiaBan(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus 1", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "asus 2", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "asus 3", "mau vand", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt1);
        expect.add(mt2);
        expect.add(mt3);
        assertEquals(expect, actual);
    }
    @Test
    public void testTimKiemTheoTen(){
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "dell", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "macbook", "mau vang", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        String keyword = "mac";
        int actual = qlmt.timTheoTen(keyword).size();
        ArrayList<MayTinh> expect = new ArrayList<>();
        expect.add(mt3);
        assertEquals(expect.size(), actual);
    }
    @Test
    public void testLuuVaDocFile() throws IOException{
        MayTinhModel qlmt = new MayTinhModel();
        MayTinh mt1 = new MayTinh(1, "asus", "mau trang", 500);
        MayTinh mt2 = new MayTinh(2, "dell", "mau den", 1500);
        MayTinh mt3 = new MayTinh(3, "macbook", "mau vang", 800);
        qlmt.themMayTinh(mt1);
        qlmt.themMayTinh(mt2);
        qlmt.themMayTinh(mt3);
        qlmt.ghiFile("maytinh.txt", qlmt.layDSMayTinh());
        ArrayList<MayTinh> actual = qlmt.layDSMayTinh();
        ArrayList<MayTinh> expect = qlmt.docFile("maytinh.txt");
        assertEquals(expect.toString(), actual.toString());
    }
}
