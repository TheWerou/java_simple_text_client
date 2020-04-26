package Data_storage;
import Data_storage.Temp_data.List_in;

import java.util.ArrayList;

/** DB_facade
 *  Class control sub class and uses it to simplyfy saveing and reading data
 */
public class DB_facade {

    private List_in send_list;                      // ArrayList class
    private List_in recive_list;                    // ArrayList class

    private String login;                           // login of user
    private String ip;                              // ip of server
    private int port;                               // port of server

    /** DB_facade()
     *  constructor of class - prepering class to work
     */
    public DB_facade(String server_ip, int port)
    {
        this.send_list = new List_in();
        this.recive_list = new List_in();
        this.login = "MACIEK";
        this.ip = server_ip;
        this.port = port;
    }

    /** getRecived_list()
     * getter get object which contain list of recived invitation
     * @return object List_in
     */
    public List_in getRecived_list() {
        return recive_list;
    }

    /** get_from_recivet_list(int e)
     * get one invitation form recivet_list
     * @param e position in list
     * @return ArrayList<String>
     */
    public ArrayList<String> get_from_recivet_list(int e)
    {
        return this.recive_list.get_from_list(e);
    }

    /** getLogin()
     * get login of user
     * @return login of user
     */
    public String getLogin() {
        return login;
    }

    /**  getPort()
     * gives port of server
     * @return port of server
     */
    public int getPort() {
        return port;
    }

    /**  getSend_list()
     * getter get object which contain list of send invitation
     * @return object List_in
     */
    public List_in getSend_list() {
        return send_list;
    }

    /**  getIp()
     * gives ip of server
     * @return ip of server
     */
    public String getIp() {
        return ip;
    }

    /** add_send_msg(ArrayList<String> input)
     * add invitation to send list
     * @param input ArrayList<String> invitation send
     */
    public void add_send_msg(ArrayList<String> input)
    {
        send_list.add_to_list(input);
    }

    /** add_recived_list(ArrayList<String> input)
     *  add invitation to send list
     * @param input ArrayList<String> invitation recived
     */
    public void add_recived_list(ArrayList<String> input)
    {
        input.remove(0);
        recive_list.add_to_list(input);
    }

    /** find_and_destroy_send_list(ArrayList<String> input)
     * Find and deletes send invitation
     * @param input invitation tah we want to delete
     */
    public void find_and_destroy_send_list(ArrayList<String> input)
    {
        this.send_list.find_and_delete(input);
    }

    /** find_and_destroy_recived_list(ArrayList<String> input)
     * Find and deletes send invitation
     * @param input invitation tah we want to delete
     */
    public void find_and_destroy_recived_list(ArrayList<String> input)
    {
        this.recive_list.find_and_delete(input);
    }

    /** clear_send_list()
     * remove all element from send list
     */
    public void clear_send_list()
    {
        this.send_list.clear_all();
    }

    /** clear_recive_list()
     *  remove all element from recive list
     */
    public void clear_recive_list()
    {
        this.recive_list.clear_all();
    }

    /** setLogin(String login)
     * change nick name
     * @param login new nick name
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /** setIp(String ip)
     * change ip of server
     * @param ip new ip of sever
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** setPort(int port)
     * sets new port
     * @param port new server port
     */
    public void setPort(int port) {
        this.port = port;
    }


}
