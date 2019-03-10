package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import data.Menu;
import data.ShoppingList;
import fragment.BuyFragment;
import page.page.page101.R;

/**
 * Created by man on 2015-09-26.
 */
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
                s_cost.setText(menu.getPrice()+" 원");
            if (s_amount != null)
                s_amount.setText(menu.getAmount()+"");



            s_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<ShoppingList.shopppinglist.size();i++){
                        if(ShoppingList.shopppinglist.get(i).getName().equals(menu.getName())){
                            ShoppingList.shopppinglist.remove(i);

                            break;

                        }
                    }
                }
            });
            

        }

        return convertView;
    }

}