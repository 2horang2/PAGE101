package fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import adapter.ViewAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import data.Menu;
import data.MenuList;
import data.ShoppingList;
import page.page.page101.BuyDetailActivity;
import page.page.page101.R;

public class BuyFragment extends Fragment {

    ListView menu_lv;








    TextView menu1;



    @Bind(R.id.buy_btn)
    Button buy;


    int use_p;

    View v;

    public static BuyFragment newInstance() {
        BuyFragment fragment = new BuyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_buy, container, false);

        ButterKnife.bind(v, getActivity());


        menu1 = (TextView) v.findViewById(R.id.menu1);
        menu_lv = (ListView) v.findViewById(R.id.menu_lv);
        buy = (Button) v.findViewById(R.id.buy);



        menu1 = (TextView) v.findViewById(R.id.menu1);

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(getActivity(), BuyDetailActivity.class);
                startActivity(it);
            }
        });


        new MenuAsync().execute();



        return v;
    }




    ProgressDialog loagindDialog;
    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    String response2;

    private class inq_id extends AsyncTask<String, Void, Void> {
        @Override


        protected void onPreExecute() {
            super.onPreExecute();
            loagindDialog = ProgressDialog.show(getActivity(), "잠시만 기다려주세요",
                    "Please wait..", true, false);
        }

        @Override

        protected Void doInBackground(String... params) {
            try {

                //id.getText().toString()
                /*

                httpclient = new DefaultHttpClient();
                httppost = new HttpPost("http://cmcm1284.cafe24.com/windmill/mountain_introduce_insert.php");
                nameValuePairs = new ArrayList<NameValuePair>(7);
                nameValuePairs.add(new BasicNameValuePair("type", category));
                nameValuePairs.add(new BasicNameValuePair("add", mountain_introduce_add.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("name", mountain_introduce_name.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("text", mountain_introduce_text.getText().toString()));

                if(mountain_introduce_ph.getText().toString().equals(""))
                    nameValuePairs.add(new BasicNameValuePair("ph", mountain_introduce_ph.getText().toString()));
                else
                    nameValuePairs.add(new BasicNameValuePair("ph","null"));

                if(mountain_introduce_url.getText().toString().equals(""))
                    nameValuePairs.add(new BasicNameValuePair("url", mountain_introduce_url.getText().toString()));
                else
                    nameValuePairs.add(new BasicNameValuePair("url","null"));

                nameValuePairs.add(new BasicNameValuePair("mountain_idx", getIntent().getStringExtra("mountain_idx")));


                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                response2 = httpclient.execute(httppost, responseHandler);


                //ResponseHandler<String> responseHandler = new BasicResponseHandler();
                // String re = httpclient.execute(httppost, responseHandler);
                //  System.out.println("멍충시발~ : " + re);

*/
            } catch (Exception e) {
                System.out.println("Exception : " + e.getMessage());
            }
            return null;
        }

        protected void onPostExecute(Void result) {
            loagindDialog.dismiss();
            use_p = Integer.valueOf(response2);
            getActivity().finish();

        }
    }

    public static ShoppingListAdapter adapter1;
    int total_cost = 0;

    class MenuAsync extends AsyncTask<String, String, ArrayList<Menu>> {
        @Override
        protected ArrayList<Menu> doInBackground(String... strings) {
            return connectMenu();
        }

        @Override
        protected void onPostExecute(ArrayList<Menu> Menus) {
            adapter1 = new ShoppingListAdapter(getActivity(), R.layout.fragment_buy, Menus);
            menu_lv.setAdapter(adapter1);
            int num = 0;
            for (int i = 0; i < ShoppingList.shopppinglist.size(); i++) {
                num += ShoppingList.shopppinglist.get(i).getAmount();
                total_cost += ShoppingList.shopppinglist.get(i).getPrice();
            }
            menu1.setText("1.메뉴 확인" + " [" + num + "]");
            setLisetViewHeight(menu_lv,ShoppingList.shopppinglist.size());
        }
    }

    private void setLisetViewHeight(final ListView listView,int count){

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        if(count == 0){
            count = listAdapter.getCount();
        }
        for (int i = 0; i < listAdapter.getCount(); i++) {
            if(i>=count)
                break;
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (count));
        listView.setLayoutParams(params);
    }



    private ArrayList<Menu> connectMenu() {
        return ShoppingList.shopppinglist;
    }


    public class ShoppingListAdapter extends ArrayAdapter<Menu> {

        ArrayList<Menu> list;

        public ShoppingListAdapter(Context context, int resource, ArrayList<Menu> objects) {
            super(context, resource, objects);
            list = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater linf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = linf.inflate(R.layout.list_item_shopping, null);
            final Menu menu = list.get(position);

            if (menu != null) {


                final TextView s_name = (TextView) convertView.findViewById(R.id.s_name);
                final TextView s_cost = (TextView) convertView.findViewById(R.id.s_price);

                final TextView s_amount = (TextView) convertView.findViewById(R.id.s_amount);
                final TextView s_delete = (TextView) convertView.findViewById(R.id.s_delete);


                if (s_name != null)
                    s_name.setText(menu.getName());
                if (s_cost != null)
                    s_cost.setText(menu.getPrice() + " 원");
                if (s_amount != null)
                    s_amount.setText(menu.getAmount() + "");


                s_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < ShoppingList.shopppinglist.size(); i++) {
                            if (ShoppingList.shopppinglist.get(i).getName().equals(menu.getName())) {
                                ShoppingList.shopppinglist.remove(i);

                                new MenuAsync().execute();
                                int num = 0;
                                for (int j = 0; i < ShoppingList.shopppinglist.size(); i++) {
                                    num += ShoppingList.shopppinglist.get(j).getAmount();
                                }
                                menu1.setText("1.메뉴 확인" + " [" + num + "]");
                                break;

                            }
                        }
                    }
                });


            }

            return convertView;
        }


    }
}
