package Main_facade.Comunication;

import Main_facade.Log_class.log_gen;

import java.io.*;

import java.net.Socket;

public class Comunication
{

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private log_gen log;
    private boolean con_made;

    public Comunication(log_gen log)
    {
        this.log = log;
        this.con_made = false;
    }

    public boolean startConnection(String ip, int port)
    {
        try
        {
            this.clientSocket = new Socket(ip, port);
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.setCon_made(true);
            return true;
        }
        catch(IOException e)
        {
            this.log.INFO_log("Can not connect");
            this.setCon_made(false);
            return false;
        }


    }

    public void sendMessage(String msg)
    {
        out.println(msg);
    }

    public String reciveMessage()
    {
        String resp = null;
        try
        {
            resp = in.readLine();
        }
        catch (IOException e)
        {
            log.INFO_log("Can not recive msg");
        }
        return resp;

    }

    public void stopConnection()
    {
        if(this.isCon_made())
        {
            try
            {
                this.in.close();
                this.out.close();
                this.clientSocket.close();
            }
            catch(IOException e)
            {
                this.log.INFO_log("Something not working");
            }
        }
        else {
            this.log.INFO_log("Not connected");
        }


    }

    private boolean isCon_made() {
        return con_made;
    }

    private void setCon_made(boolean con_made) {
        this.con_made = con_made;
    }
}