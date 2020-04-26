package com.company;

import Main_facade.Facade;
import Data_storage.DB_facade;
import Main_facade.Log_class.log_gen;
import Gui.text_gui;


/** Main
 * Main class of program
 */
public class Main {

    public static void main(String[] args)
    {

        log_gen log = new log_gen();                                        // object used to create logs in program
        DB_facade db = new DB_facade("127.0.0.1", 8123);      // object of class that storagre data
        Facade facade = new Facade(db,log);                                 // Main object of class that gives you ablity to comunicate with server (get inforamtion from server, send information)
        text_gui txt_gui = new text_gui(facade, db);                        // temporary text interface to communicate with user

        txt_gui.main_menu();


    }
}
