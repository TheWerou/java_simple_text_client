package Main_facade.Comunication;

import Main_facade.Log_class.log_gen;

import java.io.*;

import java.net.Socket;

/** Comunication
 *  Class is responsible for reciving and sending data to server or from server
 */
public class Comunication
{

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private log_gen log;
    private boolean con_made;

    /** Comunication(log_gen log)
     * Constructor of program
     * @param log object of class that handles logs in program
     */
    public Comunication(log_gen log)
    {
        this.log = log;
        this.con_made = false;
    }

    /** startConnection(String ip, int port)
     * Method estabish connection with server
     * @param ip ip to server
     * @param port port to server
     * @return true if connection was succesfull
     */
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

    /** sendMessage(String msg)
     *  This method send mesage to server
     * @param msg String that we want to send
     */
    public void sendMessage(String msg)
    {
        out.println(msg);
    }

    /** reciveMessage()
     * Method recive last send String
     * @return Recived String
     */
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

    /** stopConnection()
     *  this method ends connection
     */
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

    /** isCon_made()
     *  return true if connection was made
     * @return true if connection was made
     */
    private boolean isCon_made() {
        return con_made;
    }

    /** setCon_made(boolean con_made)
     * set flag con_made
     * @param con_made
     */
    private void setCon_made(boolean con_made) {
        this.con_made = con_made;
    }
}