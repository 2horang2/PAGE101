package adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import data.Menu;
import data.ShoppingList;
import page.page.page101.R;

/**
 * Created by man on 2015-09-26.
 */
public class ViewAdapter extends ArrayAdapter<Menu> {

    ArrayList<Menu> list;

    public ViewAdapter(Context context, int resource, ArrayList<Menu> objects) {
        super(context, resource, objects);
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater linf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = linf.inflate(R.layout.list_item_coffee, null);
        final Menu menu = list.get(position);

        if (menu != null) {

            final RelativeLayout head = (RelativeLayout) convertView.findViewById(R.id.head);

            final TextView c_name = (TextView) convertView.findViewById(R.id.c_name);
            final TextView c_cost = (TextView) convertView.findViewById(R.id.c_cost);

            final TextView cnt_m = (TextView) convertView.findViewById(R.id.cnt_m);
            final TextView cnt_p = (TextView) convertView.findViewById(R.id.cnt_p);
            final TextView cnt_num = (TextView) convertView.findViewById(R.id.cnt_num);

            final RelativeLayout shot_btn = (RelativeLayout) convertView.findViewById(R.id.shot_btn);

            final RelativeLayout cream_btn = (RelativeLayout) convertView.findViewById(R.id.cream_btn);

            final Button buy = (Button) convertView.findViewById(R.id.buy);


            shot_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    icee = new IceDialog(getContext());
                    icee.show();

                }
            });
            cream_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    creamm = new CreamDialog(getContext());
                    creamm.show();

                }
            });

            if (c_name != null)
                c_name.setText(menu.getName());
            if (c_cost != null)
                c_cost.setText(menu.getPrice() + " 원");


            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menu.setPrice(Integer.valueOf(cnt_num.getText().toString()) * menu.getPrice());
                    menu.setAmount(Integer.valueOf(cnt_num.getText().toString()));
                    ShoppingList.shopppinglist.add(menu);

                }
            });

            cnt_m.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.valueOf(cnt_num.getText().toString());
                    if (num != 1) {
                        cnt_num.setText(--num + "");
                        menu.setAmount(num);
                        c_cost.setText(num * menu.getPrice() + " 원");
                    }
                }
            });
            cnt_p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.valueOf(cnt_num.getText().toString());
                    cnt_num.setText(++num + "");
                    menu.setAmount(num);
                    c_cost.setText(num * menu.getPrice() + " 원");

                }
            });


        }

        return convertView;
    }


    CreamDialog creamm;


    public class CreamDialog extends Dialog {
        public CreamDialog(Context context) {
            super(context);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialiog_cream);


        }

    }

    IceDialog icee;

    public class IceDialog extends Dialog {
        public IceDialog(Context context) {
            super(context);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialiog_ice);


        }

    }
}