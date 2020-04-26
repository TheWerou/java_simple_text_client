package Gui;
import Data_storage.DB_facade;
import Main_facade.Facade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;

/**
 * Class that show us text interface to communicate with user (mainly for program tests)
 */
public class text_gui {
    DB_facade db;
    Scanner scan;
    Facade facade;

    /** text_gui(Facade facade, DB_facade db)
     * constructor of text_gui (Prepres class to by used)
     * @param facade obiect of class for easier server comunication
     * @param db obiect of class responsible for storage
     */
    public text_gui(Facade facade, DB_facade db) {
        this.scan = new Scanner(System.in);
        this.facade = facade;
        this.db = db;
    }

    /** main_menu()
     *  creates text main menu
     * @return if true program ends
     */
    public boolean main_menu() {
        boolean out = false;
        while (!out) {
            System.out.println("MENU");
            System.out.println("1. Ask for cofee/tea time");
            System.out.println("2. My invitations");
            System.out.println("3. Options");
            System.out.println("4. Exit");
            System.out.print("--> ");
            String ans = this.scan.nextLine();

            if (ans.equals("1")) {
                this.create_order();

            } else if (ans.equals("2")) {
                try {
                    this.check_invit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (ans.equals("3")) {
                this.Options();
            } else if (ans.equals("4")) {
                return true;
            } else {
                System.out.println("------------------------------");
                System.out.println("Wrong answer");
                System.out.println("------------------------------");
            }
        }
        return true;
    }

    /** create_order()
     * cretes window to create order
     * @return if true program ends
     */
    public boolean create_order() {
        while (true) {
            System.out.println("Type recipient name or type RETURN");
            System.out.print("-->  ");
            String ans = this.scan.nextLine();

            if (ans.equals("RETURN")) {
                return true;
            }

            if (!facade.send_ask(ans)) {
                System.out.println("Cannot connect");
            } else {
                System.out.println("Invitations send");
                return true;
            }

        }
    }

    /** check_invit()
     * Menu for reciving invitations
     * @return if true program ends
     * @throws IOException
     */
    public boolean check_invit() throws IOException {
        ArrayList<String> helper = new ArrayList<String>();

        while (true) {
            System.out.println("------------------------------");
            System.out.println("*************************************");
            if (facade.send_check()) {
                if (db.getRecived_list().lenght_of_list() < 1) {
                    System.out.println("No upcoming invitations");
                } else if (db.getRecived_list().lenght_of_list() > 5) {
                    for (int i = 0; i < 5; i++) {
                        helper = db.get_from_recivet_list(i);
                        if (helper.get(1).equals("NOO")) {
                            System.out.println("Refused tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        } else if (helper.get(1).equals("AKC")) {
                            System.out.println("Aproved tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        } else if (helper.get(1).equals("ASK")) {
                            System.out.println("Invitation for tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        }

                    }
                } else {
                    for (int i = 0; i < db.getRecived_list().lenght_of_list(); i++) {
                        helper = db.get_from_recivet_list(i);
                        if (helper.get(1).equals("NOO")) {
                            System.out.println("[" + i + "]Refused tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        } else if (helper.get(1).equals("AKC")) {
                            System.out.println("[" + i +  "]Aproved tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        } else if (helper.get(1).equals("ASK")) {
                            System.out.println("[" + i +  "]Invitation for tee time From : " + helper.get(2));
                            System.out.println("*************************************");
                        } else {
                            System.out.println("Something wrong");
                        }
                    }
                }
            } else {
                System.out.println("Connection problem");

            }

            System.out.println("------------------------------");
            System.out.println("Type number in [] to manage.");
            System.out.println("or type RETURN to return.");
            System.out.println("------------------------------");
            System.out.print("-->");

            String ans = this.scan.nextLine();
            if (ans.equals("RETURN")) {
                return true;
            }
            else
            {
                helper = db.get_from_recivet_list(Integer.parseInt(ans));
                if(helper.get(1).equals("ASK"))
                {
                    System.out.println("------------------------------");
                    System.out.println("What s your answer ?");
                    System.out.println("1. Yes");
                    System.out.println("2. NO");
                    System.out.println("------------------------------");
                    System.out.print("-->");
                    ans = this.scan.nextLine();

                    if(ans.equals("1"))
                    {
                        facade.send_reply_code(helper.get(2),0);
                    }
                    else if(ans.equals("2"))
                    {
                        facade.send_reply_code(helper.get(2),1);
                    }
                    else
                    {
                        System.out.println("------------------------------");
                        System.out.println("Wrong, option not programed");
                        System.out.println("------------------------------");
                    }

                }
                else if(helper.get(1).equals("NOO"))
                {
                    System.out.println("------------------------------");
                    System.out.println("Do you want to send invitation one more time");
                    System.out.println("1. Yes");
                    System.out.println("2. NO");
                    System.out.println("------------------------------");
                    System.out.print("-->");
                    ans = this.scan.nextLine();
                    if(ans.equals("1"))
                    {
                        facade.send_ask(helper.get(2));
                    }
                    else if(ans.equals("2"))
                    {

                        return true;
                    }
                    else
                    {
                        System.out.println("------------------------------");
                        System.out.println("Wrong, option not programed");
                        System.out.println("------------------------------");
                    }
                    facade.send_reply_code(helper.get(2),2);
                    return true;
                }
                else if(helper.get(1).equals("AKC"))
                {
                    facade.send_reply_code(helper.get(2),2);
                }
            }

        }
    }

    /**  Options()
     * Menu to configure program
     * @return if true program ends
     */
    public boolean Options() {
        while (true) {
            System.out.println("------------------------------");
            System.out.println("Type number to change ");
            System.out.println("1. present ip   " + db.getIp());
            System.out.println("2. present port " + db.getPort());
            System.out.println("3. present nick name" + db.getLogin());
            System.out.println("RETURN. return to menu");
            System.out.println("------------------------------");

            String ans = this.scan.nextLine();

            if(ans.equals("1")) {
                    System.out.println("Type new ip or RETURN to return");
                    ans = this.scan.nextLine();
                    ans = ans.toUpperCase();
                    if (ans.equals("RETURN")) {
                        return false;
                    } else {
                        db.setIp(ans);
                        return true;
                    }

                } else if (ans.equals("2")) {
                    System.out.println("Type new port or RETURN to return");
                    ans = this.scan.nextLine();

                    if (ans.equals("RETURN")) {
                        return false;
                    } else {
                        db.setPort(Integer.parseInt(ans));
                        return true;
                    }
                } else if (ans.equals("3")) {
                    System.out.println("Type nick name or RETURN to return");
                    ans = this.scan.nextLine();
                    ans = ans.toUpperCase();
                    if (ans.equals("RETURN")) {
                        return false;
                    } else {
                        db.setLogin(ans);
                        return true;
                    }

                } else if (ans.equals("RETURN")) {
                    return true;
                } else {
                    cls();
                    System.out.println("------------------------------");
                    System.out.println("Wrong answer");
                    System.out.println("------------------------------");
                }
            }
        }

    /**
     * clear window
     */
    public void cls() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // handle exept
        }
    }

}



