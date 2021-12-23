/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class MayTinhModel {

    ArrayList<MayTinh> listMayTinh = new ArrayList<>();

    /*
    1. Lay ra danh sach may tinh
     */
    public ArrayList<MayTinh> layDSMayTinh() {
        return listMayTinh;
    }

    /*
    2. Them thong tin 1 may tinh
     */
    public boolean themMayTinh(MayTinh mayTinh) {
        boolean result = true;
        if (!listMayTinh.isEmpty()) {
            for (int i = 0; i < listMayTinh.size(); i++) {
                if (listMayTinh.get(i).getMaMayTinh() == mayTinh.getMaMayTinh()) {
                    result = false;
                    break;
                } else if (i == listMayTinh.size() - 1) {
                    listMayTinh.add(mayTinh);
                }
            }
        } else {
            listMayTinh.add(mayTinh);
        }
        return result;
    }

    /*
    3. Xoa 1 may tinh ra khoi danh sach
     */
    public boolean xoaMayTinh(MayTinh mayTinh) {
        boolean result = false;
        MayTinh sp = new MayTinh();
        for (MayTinh mayTinh1 : listMayTinh) {
            if (mayTinh1.getMaMayTinh() == mayTinh.getMaMayTinh()) {
                sp = mayTinh1;
            }
        }
        if (sp != null) {
            listMayTinh.remove(sp);
            result = true;
        }
        return result;
    }

    /*
    4. Sua thong tin mot may tinh
     */
    public void suaThongTinMayTinh(MayTinh mayTinh) {
        for (MayTinh mayTinh1 : listMayTinh) {
            if (mayTinh1.getMaMayTinh() == mayTinh.getMaMayTinh()) {
                mayTinh1.setTenMayTinh(mayTinh.getTenMayTinh());
                mayTinh1.setMauSac(mayTinh.getMauSac());
                mayTinh1.setGiaBan(mayTinh.getGiaBan());
            }
        }
    }

    /*
    5. Sap xep theo 1 tieu chi: gia ban giam dan
     */
    public ArrayList<MayTinh> sapXepTheoGia() {
        ArrayList<MayTinh> result = this.listMayTinh;
        Collections.sort(result, new Comparator<MayTinh>() {
            @Override
            public int compare(MayTinh mt1, MayTinh mt2) {
                if (mt1.getGiaBan() < mt2.getGiaBan()) {
                    return 1;
                } else if (mt1.getGiaBan() > mt2.getGiaBan()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return result;
    }
   
    /*
    6. Sap xep theo 2 tieu chi: ten may tinh tang dan, gia may tinh tang dan
     */
    public ArrayList<MayTinh> sapXepTheoTenVaGia() {
        ArrayList<MayTinh> result = this.listMayTinh;
        Collections.sort(result, new Comparator<MayTinh>() {
            @Override
            public int compare(MayTinh mt1, MayTinh mt2) {
                if (mt1.getTenMayTinh().compareTo(mt2.getTenMayTinh()) > 1) {
                    return 1;
                } else if (mt1.getTenMayTinh().compareTo(mt2.getTenMayTinh()) < 1) {
                    return -1;
                } else {
                    if (mt1.getGiaBan() == mt2.getGiaBan()) {
                        return 0;
                    } else if (mt1.getGiaBan() < mt2.getGiaBan()) {
                        return 1;
                    } else {
                        return 1;
                    }
                }
            }

        });
        return result;
    }
    /*
    7. Tim theo tu khoa gan dung
     */

    public ArrayList<MayTinh> timTheoTen(String keyword) {
        ArrayList<MayTinh> result = new ArrayList<>();
        String pattern = ".*" + keyword + ".*";
        for (int i = 0; i < listMayTinh.size(); i++) {
            MayTinh mt = listMayTinh.get(i);
            if (mt.getTenMayTinh().matches(pattern)) {
                result.add(mt);
            }
        }
        return result;
    }
    /*
    8. Luu danh sach xuong file
     */

    public boolean ghiFile(String s, ArrayList<MayTinh> list) {
        boolean kq = true;
        File f = new File(s);
        try {
            FileWriter fw = new FileWriter(f, true);
            String value = "";

            for (MayTinh list1 : list) {
                value = list1.getMaMayTinh() + "-" + list1.getTenMayTinh() + "-" + list1.getMauSac() + "-" + list1.getGiaBan();
                value = value.replace(',', '.');
                fw.write(value + "\n");
            }
            fw.close();
        } catch (Exception ex) {
            kq = false;
            ex.printStackTrace();
        }
        return kq;
    }

    /*
    9. Doc danh sach tu file
     */
    public ArrayList<MayTinh> docFile(String s) throws IOException {
        listMayTinh.clear();
        File f = new File(s);
        FileReader fr;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] values = null;
            while ((line = br.readLine()) != null) {
                values = line.split("-");
                MayTinh x = new MayTinh(Integer.parseInt(values[0]), values[1], values[2], Double.parseDouble(values[3]));
                listMayTinh.add(x);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return listMayTinh;
    }

    public int demSoLuong() {
        return listMayTinh.size();
    }
}
