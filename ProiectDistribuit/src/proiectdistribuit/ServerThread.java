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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MindSlave
 */
public class ServerThread extends Thread{
    Socket socket;
    boolean connect=false;
    PrintWriter out;
    BufferedReader in;
    Server serv;
    String nick="default";

    public ServerThread() {
    }

    public ServerThread(Socket socket, boolean connect,Server serv) {
        this.socket = socket;
        this.connect = connect;
        this.serv = serv;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }




    @Override
    public void run() {
        while(connect){//loop asculta thread
            try {
                String msg=in.readLine();
                if(msg!=null){
                verifica(msg);   
                }
            } catch (IOException ex) {

            }
        }
    }
    
    
    public void verifica(String text){
        if(text.toUpperCase().startsWith("LGI")){
            login(text);
        }
        if(text.toUpperCase().startsWith("LIST")){
            trimitelista();
        }
        if(text.toUpperCase().startsWith("PRV")){
            trimiteprivat(text);
        }
        if(text.toUpperCase().startsWith("PUB")){
            trimitepublic(text);
        }
        if(text.toUpperCase().startsWith("INF")){
            info();
        }
        if(text.toUpperCase().startsWith("LGO")){
            logout();
        }
    }
    public void login(String txt){
        if(verificanick(txt.split("«")[1])){
            this.nick=txt.split("«")[1];
            send("OK");
        }
        else{
            send("NICK");
        }
    }
    public void trimitelista(){
        String lista="";
        for(int i=0;i<serv.clienti.size();i++){
           lista=lista+"«"+serv.clienti.elementAt(i).nick+"«"+serv.clienti.elementAt(i).socket.getInetAddress().getHostAddress();
        }
        for(int i=0;i<serv.clienti.size();i++){
        serv.clienti.elementAt(i).send("LISTA"+lista);
        }
    }
    public void trimitepublic(String txt){
        for(int i=0;i<serv.clienti.size();i++){
            serv.clienti.elementAt(i).send(txt);
        }       
    }
    public void trimiteprivat(String txt){
        for(int i=0;i<serv.clienti.size();i++){
            if(serv.clienti.elementAt(i).nick.equals(txt.split("«")[2])){
            serv.clienti.elementAt(i).send("PRV«"+txt.split("«")[1]+"«"+txt.split("«")[3]);
            }
        } 
    }
    public void info(){
        try {
            send("INF«SERVER IP: "+InetAddress.getLocalHost().getHostAddress()+" SERVER NAME: "+InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void logout(){
        serv.stergeclient(serv.clienti.indexOf(this));
        trimitelista();
       this.connect=false;
    }

    synchronized void send(String msg){
        out.println(msg);
    }

    public boolean verificanick(String nick){
        boolean x=false;
        for(int i=0;i<serv.clienti.size();i++){
            if(i<serv.clienti.size()-1){
           if(serv.clienti.elementAt(i).nick.equals(nick)){
              x=false;
              break;
           }
           }
           if(i==serv.clienti.size()-1){
               if(serv.clienti.elementAt(i).nick.equals(nick)){
              x=false;
              break;
           }
                else{
                x=true;
               }
           }
        }
        return x;
    }




    
}
