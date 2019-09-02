/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Servidor.FrmServidor;

/**
 *
 * @author alvar
 */
public class HiloBomba2 extends Thread {

    int segu = 30;

    public HiloBomba2(String name) {
        super(name);
    }

    @Override
    public void run() {

        FrmServidor.lbl2.setText(segu + "");

        while (true) {

            try {

                sleep(1000);
                segu--;
                FrmServidor.lbl2.setText(segu + "");

                if (segu == 0) {
                    FrmServidor.btn1112.setEnabled(true);
                    System.out.println("La bomba exploto");
                    JOptionPane.showMessageDialog(null, "LA bomba exploto");

                    FrmServidor.h2.stop();

                }

            } catch (InterruptedException ex) {
                Logger.getLogger(HiloBomba1.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
