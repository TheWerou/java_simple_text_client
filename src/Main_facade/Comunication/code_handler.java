package Main_facade.Comunication;

import java.util.ArrayList;


/** code_handler
 *  This class help convert recived string to list
 */
public class code_handler
{
    /** string_to_list(String input)
     * Convert string to list
     * @param input String that we want to be a list
     * @return ArrayList<String>
     */
    public ArrayList<String> string_to_list(String input)
    {
        ArrayList<String> helper = new ArrayList<String>();
        String[] h2 = input.split(" ");
        try{
            for(int i=0;i<h2.length;i++)
            {
                helper.add(h2[i]);

            }
        }
        catch(IndexOutOfBoundsException e)
        {
            return helper;
        }

        return helper;
    }
}

