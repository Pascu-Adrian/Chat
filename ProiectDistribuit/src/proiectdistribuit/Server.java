/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MindSlave
 */
public class Server extends Thread {
    boolean connect=false;
    ServerSocket serverSocket;
    Socket socket;
    ServerThread client;
    Vector<ServerThread> clienti;
    public String numeserver;
    public String ipserver;
    InetAddress ia;
    ServerLogGUI slg;

    public Server(boolean connect,ServerLogGUI slg) {
        this.connect=connect;
        this.slg=slg;
        make();

    }

    @Override
    public void run() { 
        while(connect){
            adaugainmonitor("");
            try {
                socket = serverSocket.accept();
                
            } catch (IOException ex) {
                
            }
                client=new ServerThread(socket,true,this);
                client.start();
                adaugaclient(client);
                adaugainmonitor("S-a conectat clientul: "
                        +client.socket.getInetAddress().getHostName()+
                        " "+client.socket.getInetAddress().getHostAddress());
        }
    }

    public void make(){
        try {
            numeserver = ia.getLocalHost().getHostName();
            ipserver=ia.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nu se poate realiza host pe portul 9999:"+ex, "Eroare creere server", JOptionPane.ERROR_MESSAGE);
            slg.dispose();
            this.interrupt();
        }
    client=null;
    clienti=new Vector<ServerThread>();
    socket=null;
    }


    void adaugaclient(ServerThread s){
        clienti.add(s);
    }
    void stergeclient(int index){
        clienti.removeElementAt(index);
        adaugainmonitor("");
    }
    void adaugainmonitor(String str){
        slg.adaugaText(str+"\n "+clienti.size()+" clienti");
    }



}
