/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Servidor.FrmServidor;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alvar
 */
public class Servidor implements Runnable {

    Socket cliente = null;
    boolean win1, win2;
    static int port = 0;
    boolean status = true;
    ServerSocket servidor = null;
    DataInputStream flujoEntrada;
    private FrmServidor qui;
    String mensaje = "";
    int totalSolicitudes = 0;

    @Override
    public void run() {
        try {
            servidor = new ServerSocket(port);
            System.out.println("Server online escuchando por el ouerto " + port);

            while (status) {
                System.out.println("Server esperando la solicitud....");
                cliente = servidor.accept();
                System.out.println("Se recibio la solicitud");

                totalSolicitudes++;
                flujoEntrada = new DataInputStream(cliente.getInputStream());
                mensaje = flujoEntrada.readUTF();
                validarMensaje();

                cliente.close();
            }
        } catch (IOException e) {
            System.out.println("Error" + e);
        }
    }

    public void validarMensaje() {
        if (mensaje.equals("negro1") && FrmServidor.bomba1 == 1) {
            FrmServidor.h1.stop();
            win1 = true;
        } else if (mensaje.equals("rojo1") && FrmServidor.bomba1 == 2) {
            FrmServidor.h1.stop();
            win1 = true;
        } else if (mensaje.equals("azul1") && FrmServidor.bomba1 == 3) {
            FrmServidor.h1.stop();
            win1 = true;
        } else if (mensaje.equals("negro2") && FrmServidor.bomba1 == 1) {
            FrmServidor.h2.stop();
            win2 = true;
        } else if (mensaje.equals("rojo2") && FrmServidor.bomba1 == 2) {
            FrmServidor.h2.stop();
            win2 = true;
        } else if (mensaje.equals("azul2") && FrmServidor.bomba1 == 3) {
            FrmServidor.h2.stop();
            win2 = true;
        } else if (win1 == true && win2 == true) {
            desconectar();
        } else {
            FrmServidor.h1.stop();
            FrmServidor.h2.stop();
            desconectar();

        }
    }

    public Servidor(FrmServidor qui, int puerto) {
        this.qui = qui;
        port = puerto;
        new Thread(this).start();
    }

    public void desconectar() {
        status = false;
        try {

            if (flujoEntrada != null) {
                flujoEntrada.close();
            }
            if (cliente != null) {
                cliente.close();

            }
            if (servidor != null) {
                servidor.close();
            }
        } catch (IOException e) {
            System.out.println("Error desconectado servidor");
        }
    }
}
