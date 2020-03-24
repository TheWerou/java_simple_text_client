package Main_facade.Comunication;

import java.util.ArrayList;

public class code_handler
{

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

