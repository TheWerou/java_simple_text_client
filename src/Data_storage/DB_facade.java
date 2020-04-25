package Data_storage;
import Data_storage.Temp_data.List_in;

import java.util.ArrayList;

public class DB_facade {

    private List_in send_list;
    private List_in recive_list;

    private String login;
    private String ip;
    private int port;

    public DB_facade()
    {
        this.send_list = new List_in();
        this.recive_list = new List_in();
        this.login = "MACIEK";
        this.ip = "192.168.0.108";
        this.port = 8123;
    }

    public List_in getRecived_list() {
        return recive_list;
    }
    public ArrayList<String> get_from_recivet_list(int e)
    {
        return this.recive_list.get_from_list(e);
    }

    public String getLogin() {
        return login;
    }

    public int getPort() {
        return port;
    }

    public List_in getSend_list() {
        return send_list;
    }

    public String getIp() {
        return ip;
    }

    public void add_send_msg(ArrayList<String> input)
    {
        send_list.add_to_list(input);
    }

    public void add_recived_list(ArrayList<String> input)

    {
        input.remove(0);
        recive_list.add_to_list(input);
    }
    public void find_and_destroy_send_list(ArrayList<String> input)
    {
        this.send_list.find_and_delete(input);
    }

    public void find_and_destroy_recived_list(ArrayList<String> input)
    {
        this.recive_list.find_and_delete(input);
    }



    public void clear_send_list()
    {
        this.send_list.clear_all();
    }

    public void clear_recive_list()
    {
        this.recive_list.clear_all();
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }


}
