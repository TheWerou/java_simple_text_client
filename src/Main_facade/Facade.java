package Main_facade;
import Main_facade.Comunication.Comunication;
import Main_facade.Comunication.code_handler;
import Data_storage.DB_facade;
import Main_facade.Log_class.log_gen;

import java.util.ArrayList;

public class Facade {
    private DB_facade db;
    private code_handler code;
    private Comunication com;
    private log_gen log;
    private String ip;
    private int port;

    public Facade(DB_facade db,log_gen log)
    {
        this.ip = db.getIp();
        this.port = db.getPort();
        this.log = log;
        this.com = new Comunication(this.log);
        this.code = new code_handler();
        this.db = db;

    }
    public boolean send_ask(String to_who)
    {
        ArrayList<String> hlp = new ArrayList<String>();
        String helper = "ASK " + to_who;
        if(this.start_com(helper))
        {
            helper = to_who + " ASK " + this.db.getLogin();
            String msg = com.reciveMessage();

            if(msg.equals("END"))
            {
                hlp = this.code.string_to_list(helper);
                this.db.add_send_msg(hlp);
                com.stopConnection();
                return  true;
            }
            else
            {
                com.stopConnection();
                return  false;
            }
        }
        else {

            return false;
        }

    }
    public boolean send_check()
    {
        ArrayList<String> list_com = new ArrayList<String>();
        this.db.clear_recive_list();

        if(this.start_com("CHK"))
        {
            ArrayList<String> helper = new ArrayList<String>();
            String msg = com.reciveMessage();
            helper = this.code.string_to_list(msg);

            if(helper.get(0).equals("LST"))
            {
                int i=0;
                try {

                    while(true)
                    {

                        msg = com.reciveMessage();
                        helper = this.code.string_to_list(msg);
                        if(!helper.get(0).equals("END"))
                        {
                            this.db.add_recived_list(helper);
                        }
                        else
                        {
                            // tu trze3ba dopisac
                            com.stopConnection();
                            return true;
                        }
                        i++;
                    }
                }
                catch (NullPointerException e)
                {
                    com.stopConnection();
                    return false;
                }


            }
            else if(helper.get(0).equals("NOT"))
            {
                return false;
            }
            else
            {
                return false;
            }
        }
        else {
            return false;
        }


    }

    public boolean send_reply_code(String to_who, int choice)
    {
        ArrayList<String> hlp = new ArrayList<String>();
        String helper;
        int helper2 = choice;

        if(helper2 == 0) { helper = "AKC " + to_who; }
        else if (helper2 == 1) { helper = "NOO " + to_who; }
        else { helper = "RCV " + to_who; }

        if(this.start_com(helper))
        {
            String msg = com.reciveMessage();

            if(msg.equals("END"))
            {
                hlp = this.code.string_to_list(helper);
                this.db.find_and_destroy_recived_list(hlp);
                this.db.find_and_destroy_send_list(hlp);

            }

            com.stopConnection();
            log.INFO_log("connection ended sucesfully");
            return  true;
        }
        else {
            com.stopConnection();
            log.INFO_log("connection ended sucesfully");
            log.WARNING_log("something goes wrong (WRG msg)");
            return false;
        }
    }

    private boolean start_com(String type)
    {
        if(com.startConnection(this.ip, this.port))
        {
            log.CONFIG_log("connection made to ip- " + this.ip + " port " + this.port);
            String msg = com.reciveMessage();
            com.sendMessage(this.db.getLogin());
            msg = com.reciveMessage();

            if(msg.equals("WELCOME"))
            {
                com.sendMessage(type);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            log.INFO_log("cannnot connect to server");
            return false;
        }

    }

}
