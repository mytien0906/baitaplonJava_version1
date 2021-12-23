/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynh;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.lang.System.Logger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class MayTinhController {

    MayTinhView view;
    MayTinhModel model;
    DecimalFormat format = new DecimalFormat();

    public MayTinhController(MayTinhView view, MayTinhModel model) {
        this.view = view;
        this.model = model;
        hienThi(model.layDSMayTinh());
        view.getButtonXoa().setEnabled(false);
        view.getButtonSua().setEnabled(false);
        view.getButtonGhiFile().setEnabled(false);
        view.getButtonDocFile().setEnabled(true);
        view.getButtonThem().addActionListener(themListener());
        view.getButtonSua().addActionListener(suaListener());
        view.getButtonXoa().addActionListener(xoaListener());
        view.getButtonTim().addActionListener(timKiem());
        view.getButtonGhiFile().addActionListener(ghiFile());
        view.getButtonDocFile().addActionListener(docFile());
        view.getComboBoxSapXep().addActionListener(chooseOption());
        view.getTableMayTinh().addMouseListener(tableListener());
        view.setVisible(true);

    }

    /*
    1. Lay ra danh sach may tinh
     */
    public void hienThi(ArrayList<MayTinh> list) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTableMayTinh().getModel();
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Mã máy tính");
        tableModel.addColumn("Tên máy tính");
        tableModel.addColumn("Màu sắc");
        tableModel.addColumn("Giá bán");
        for (MayTinh mayTinh : list) {
            tableModel.addRow(new Object[]{
                mayTinh.getMaMayTinh(), mayTinh.getTenMayTinh(), mayTinh.getMauSac(), format.format(mayTinh.getGiaBan())
            });
        }
        view.getTableMayTinh().setModel(tableModel);
    }

    /*
    Thiết lập lại các trạng thái của button và text field
     */
    public void resetText() {
        view.getTextFieldMaMayTinh().setText(null);
        view.getTextFieldTenMayTinh().setText(null);
        view.getTextFieldMauSac().setText(null);
        view.getTextFieldGiaBan().setText(null);
        view.getTextFieldTimTen().setText(null);
        view.getButtonThem().setEnabled(true);
        view.getButtonTim().setEnabled(true);
        view.getButtonXoa().setEnabled(false);
        view.getButtonSua().setEnabled(false);
        view.getButtonDocFile().setEnabled(true);
        view.getButtonGhiFile().setEnabled(true);
        if (model.demSoLuong() == 0) {
            view.getButtonThem().setEnabled(true);
            view.getButtonTim().setEnabled(false);
            view.getButtonXoa().setEnabled(false);
            view.getButtonSua().setEnabled(false);
            view.getButtonDocFile().setEnabled(false);
            view.getButtonGhiFile().setEnabled(false);
        }
    }

    /*
    2. Them thong tin 1 may tinh
     */
    private ActionListener themListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MayTinh mt = new MayTinh(Integer.parseInt(view.getTextFieldMaMayTinh().getText()), view.getTextFieldTenMayTinh().getText(), view.getTextFieldMauSac().getText(), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                    model.themMayTinh(mt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                resetText();
                hienThi(model.layDSMayTinh());
            }
        };
    }

    /*
    3. Xoa 1 may tinh ra khoi danh sach
     */
    private ActionListener xoaListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MayTinh mt = new MayTinh(Integer.parseInt(view.getTextFieldMaMayTinh().getText()), view.getTextFieldTenMayTinh().getText(), view.getTextFieldMauSac().getText(), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                String maMayTinh = view.getTextFieldMaMayTinh().getText();
                System.out.println(maMayTinh);
                if (!(maMayTinh.equals(""))) {
                    int confirm = JOptionPane.showConfirmDialog(view,
                            "Bạn có muốn xóa máy tính có mã '" + maMayTinh + "' ?");
                    if (confirm == 0) {
                        try {
                            model.xoaMayTinh(mt);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(view, ex.toString());
                        }
                    }
                }
                resetText();
                hienThi(model.layDSMayTinh());
            }
        };
    }

    /*
    4. Sua thong tin mot may tinh
     */
    private ActionListener suaListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MayTinh mt = new MayTinh(Integer.parseInt(view.getTextFieldMaMayTinh().getText()), view.getTextFieldTenMayTinh().getText(), view.getTextFieldMauSac().getText(), Double.parseDouble(view.getTextFieldGiaBan().getText()));
                    model.suaThongTinMayTinh(mt);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                resetText();
                view.getButtonThem().setEnabled(true);
                hienThi(model.layDSMayTinh());
            }
        };
    }

    /*
    5. Sap xep theo 1 tieu chi: gia ban giam dan
    6. Sap xep theo 2 tieu chi: ten may tinh tang dan, gia may tinh tang dan
     */
    int index = 0;
    private ActionListener chooseOption() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index = view.getComboBoxSapXep().getSelectedIndex();
                if (index == 1) {
                    try {
                        model.sapXepTheoGia();
                        hienThi(model.layDSMayTinh());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex.toString());
                    }
                } else if (index == 2) {
                    try {
                        model.sapXepTheoTenVaGia();
                        hienThi(model.layDSMayTinh());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex.toString());
                    }
                }
            }
        };
    }

    /*
    7. Tim theo tu khoa gan dung
     */
    private ActionListener timKiem() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = view.getTextFieldTimTen().getText();
                if (!(keyword.equals(""))) {
                    try {
                        model.timTheoTen(keyword);
                        resetText();
                        hienThi(model.timTheoTen(keyword));
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(view, ex.toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Bạn chưa nhập từ khóa để tìm kiếm");
                }
                if (model.timTheoTen(keyword).size() == 0) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy từ khóa của bạn! Vui lòng nhập từ khác");
                    hienThi(model.layDSMayTinh());
                } else {
                    System.out.println(keyword);
                    model.timTheoTen(keyword);

                }
                view.getTextFieldTimTen().setText(null);
                view.getButtonTim().setEnabled(true);
            }
        };
    }

    /*
    8. Luu danh sach xuong file
     */
    private ActionListener ghiFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(view, "Bạn muốn lưu dữ liệu xuống file?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (model.ghiFile("maytinh.txt", model.layDSMayTinh())) {
                        JOptionPane.showMessageDialog(view, "Lưu thành công");
                    } else {
                        JOptionPane.showMessageDialog(view, "Lưu thất bại");
                    }
                }
            }
        };
    }

    /*
    9. Doc danh sach tu file
     */
    private ActionListener docFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    model.docFile("maytinh.txt");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.toString());
                }
                hienThi(model.layDSMayTinh());
            }
        };
    }

    //mouse event
    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int dong = view.getTableMayTinh().getSelectedRow();
                view.getTextFieldMaMayTinh().setText(view.getTableMayTinh().getValueAt(dong, 0).toString());
                view.getTextFieldTenMayTinh().setText(view.getTableMayTinh().getValueAt(dong, 1).toString());
                view.getTextFieldMauSac().setText(String.valueOf(view.getTableMayTinh().getValueAt(dong, 2).toString()));
                view.getTextFieldGiaBan().setText(String.valueOf(view.getTableMayTinh().getValueAt(dong, 3).toString()));
                view.getButtonThem().setEnabled(true);
                view.getButtonTim().setEnabled(true);
                view.getButtonSua().setEnabled(true);
                view.getButtonXoa().setEnabled(true);
            }
        };
    }
}
