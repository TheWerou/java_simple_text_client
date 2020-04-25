package com.company;

import Main_facade.Facade;
import Data_storage.DB_facade;
import Main_facade.Log_class.log_gen;
import Gui.text_gui;

public class Main {

    public static void main(String[] args)
    {
        log_gen log = new log_gen();
        DB_facade db = new DB_facade();
        Facade facade = new Facade(db,log);
        text_gui txt_gui = new text_gui(facade, db);

        txt_gui.main_menu();


    }
}
