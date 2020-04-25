package Data_storage.Temp_data;

import java.util.ArrayList;

public class List_in
{
    ArrayList<ArrayList<String>> mylist;

    public List_in()
    {
        this.mylist = new ArrayList<>();
    }

    public void add_to_list(ArrayList<String> list)
    {
        mylist.add(list);
    }
    public ArrayList<String> get_from_list(int e)
    {
        return mylist.get(e);
    }

    public ArrayList<ArrayList<String>> getMylist() {
        return mylist;
    }

    public  int lenght_of_list()
    {
        return mylist.size();
    }

    public void find_and_delete(ArrayList<String> input)
    {
        mylist.remove(input);
    }

    public void clear_all()
    {
        mylist.clear();
    }
}
