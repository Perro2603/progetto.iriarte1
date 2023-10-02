package com.example;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStr {
    
    String nomeServer = "localhost";
    int portaServer = 3069;
    Socket miSocket;
    BufferedReader tastiera;
    String stringaUtente ;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;


    public Socket connetti(){

        System.out.println(" client partito in esecuzione ...");

        try {
            tastiera =  new BufferedReader(new InputStreamReader(System.in));
            
            miSocket = new Socket(nomeServer, portaServer);


            outVersoServer = new DataOutputStream(miSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));

        }catch (UnknownHostException e){

                System.err.println("host sconosciuto");
        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Errore durante la esecuzione ");
            System.exit(1);
        }
        return miSocket;
    }
    

    public void comunica(){

        try {
            
            System.out.println(" inserisci la stringa da trasmettere al server : "+'\n');
            stringaUtente = tastiera.readLine();

            System.out.println(" invio la stringa al server e attendo....");

            outVersoServer.writeBytes(stringaUtente+'\n');

            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println(" risposta dal server " + '\n' + stringaRicevutaDalServer);

            System.out.println("client termina elaborazione e chiude connessione");

            miSocket.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Errore durante la esecuzione col server");
            System.exit(1);
            
            // TODO: handle exception
        }
    }
}
