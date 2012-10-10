/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proiectdistribuit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MindSlave
 */
public class ClientThread extends Thread{
    int port;
    String address;
    Client cl;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    Vector<String> user;
    Vector<Vector> vectoruseri=new Vector<Vector>();
    String[] listast;
    String nick;
    boolean ok=false;

    public ClientThread() {
    }

    public ClientThread(int port, String address, Client cl,boolean ok) {
        this.port = port;
        this.address = address;
        this.cl = cl;
        this.ok=ok;
        try {
            socket = new Socket(InetAddress.getByName(address), port);
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        nick=JOptionPane.showInputDialog(null, "Introduceti un nick:",
                "Introduceti nick-ul", JOptionPane.PLAIN_MESSAGE);
        login();
    }

    @Override
    public void run() {
       while(ok){//loop ascultare server
            try {
                verifica(in.readLine());
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }



    public void verifica(String text){
        if(text.toUpperCase().startsWith("OK")){
            deschide();
        }
        if(text.toUpperCase().startsWith("NICK")){
            altnick();
        }
        if(text.toUpperCase().startsWith("LIST")){
            incarcalista(text);
        }
        if(text.toUpperCase().startsWith("PRV")){
            privat(text);
        }
        if(text.toUpperCase().startsWith("PUB")){
            publicmsg(text);
        }
        if(text.toUpperCase().startsWith("INF")){
            infoserver(text);
        }
        if(text.toUpperCase().startsWith("LGO")){
            inchide();
        }
    }
    public void login(){
        send("LGI«"+nick);

    }
    public void deschide(){
        cl.deschideclient(nick);
        cl.cg.repaint();
        this.ok=true;
        send("LIST");
    }
    public void altnick(){
        nick=JOptionPane.showInputDialog(null, "CONFLICT DE NICK!\n Introduceti alt nick:",
                "Introduceti nick-ul", JOptionPane.PLAIN_MESSAGE);
        send("LGI«"+nick);
    }
    public void incarcalista(String txt){
        String[] str=txt.split("«");
        Vector<String> v=new Vector<String>();
        Vector<Vector<String>> v1=new Vector<Vector<String>>();
        for(int i=1;i<str.length;i++){
            v=new Vector<String>();
            v.add(str[i]);
            i++;
            v.add(str[i]);
            v1.add(v);
        }
        cl.cg.incarcaLista(v1);
    }
    public void privat(String txt){
        cl.cg.privat(txt);
    }
    public void publicmsg(String txt){
        String[] str=txt.split("«");
        cl.cg.adaugaText(str[1]+":"+str[2]);
    }
    public void infoserver(String txt){
        String[] str=txt.split("«");
        cl.cg.adaugaText(str[2]);
    }
    public void inchide(){
        JOptionPane.showMessageDialog(null, "SERVERUL A INTRERUPT CONEXIUNEA", "CONEXIUNE INTRERUPTA", JOptionPane.ERROR_MESSAGE);
        this.ok=false;
        cl.cg.deconecteaza();
    }
    synchronized void send(String msg){
        System.out.println("Spre server:"+msg);
        out.println(msg);
    }






}
