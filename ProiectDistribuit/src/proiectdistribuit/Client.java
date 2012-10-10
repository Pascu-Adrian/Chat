/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import javax.swing.JOptionPane;

/**
 *
 * @author MindSlave
 */
public class Client {
    int port;
    String address;
    MainGUI mg;
    public ClientGUI cg=new ClientGUI();
    ClientThread ct;

    public Client() {
    }

    public Client(int port, String address, MainGUI mg) {
        this.port = port;
        this.address = address;
        this.mg = mg;
        ct=new ClientThread(port, address, this,true);
        ct.start();
    }  
    public void deschideclient(String nick){
        cg=new ClientGUI(this,nick);
    }




}
