package fragment_coffe;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.ViewAdapter;
import data.Menu;
import data.MenuList;
import page.page.page101.MainActivity;
import page.page.page101.R;

public class Coffee extends Fragment implements AdapterView.OnItemClickListener{

    public static Coffee newInstance() {
        Coffee fragment = new Coffee();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public Coffee() {
        // Required empty public constructor
    }

    private ListView ex1_list;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_all2, container, false);
        ex1_list = (ListView)v.findViewById(R.id.lv);
        ex1_list.setOnItemClickListener(this);
        new MenuAsync().execute();
        return v;

    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), position, Toast.LENGTH_LONG).show();
        ex1_list.setSelectionFromTop(position, 0);
    }





    private ViewAdapter adapter1;
    class MenuAsync extends AsyncTask<String, String, ArrayList<Menu>> {
        @Override
        protected ArrayList<Menu> doInBackground(String... strings) {
            return connectMenu();
        }
        @Override
        protected void onPostExecute(ArrayList<Menu> Menus) {
            adapter1 = new ViewAdapter(getActivity(), R.layout.fragment_all2, Menus);
            ex1_list.setAdapter(adapter1);
        }
    }
    private ArrayList<Menu> connectMenu() {
        return MenuList.list;
    }

}
