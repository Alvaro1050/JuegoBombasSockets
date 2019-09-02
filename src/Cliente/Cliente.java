/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Cliente.FrmCliente;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author alvar
 */
public class Cliente {

    Socket cliente;
    private FrmCliente gui;
    String mensaje = ";";
    DataOutputStream flujosalida;

    public Cliente(FrmCliente gui) {
        this.gui = gui;
    }

    public void enviarMensaje(String mensaje, int puerto, String ip) {
        try {
            cliente = new Socket(ip, puerto);
            flujosalida = new DataOutputStream(cliente.getOutputStream());
            flujosalida.writeUTF(mensaje);
            cliente.close();

        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }

    public void descoenctar() {
        try {
            if (flujosalida != null) {
                flujosalida.close();
            }
            if (cliente != null) {
                cliente.close();
            }
        } catch (IOException e) {
            System.out.println("error" + e);
        }
    }

}
