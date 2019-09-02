/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Servidor.FrmServidor;

/**
 *
 * @author alvar
 */
public class HiloBomba1 extends Thread {

    int segu = 30;

    public HiloBomba1(String name) {
        super(name);
    }

    @Override
    public void run() {

        FrmServidor.lbl1.setText(segu + "");

        while (true) {

            try {

                sleep(1000);
                segu--;
                FrmServidor.lbl1.setText(segu + "");

                if (segu == 0) {
                    FrmServidor.btn1112.setEnabled(true);
                    System.out.println("La bomba exploto");
                    JOptionPane.showMessageDialog(null, "LA bomba exploto");
                    FrmServidor.h1.stop();

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(HiloBomba1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
