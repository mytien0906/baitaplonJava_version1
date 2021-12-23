/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huynh;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MainMayTinh {

    public static void main(String[] args) throws IOException {
        new MayTinhController(new MayTinhView(), new MayTinhModel());
//        MayTinhModel model = new MayTinhModel();
//        MayTinhView view = new MayTinhView();
//        hienThi(model.layDSMayTinh());

    }
}
