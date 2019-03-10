package page.page.page101;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import fragment.AllFragment;
import fragment.BuyFragment;
import fragment.EventFragment;
import fragment.QFragment;
import fragment.WebFragment;
import page.page.page101.R;

public class MainActivity extends AppCompatActivity {


    LinearLayout menu1,menu2,menu3,menu4,menu5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu1 = (LinearLayout) findViewById(R.id.menu1);
        menu2 = (LinearLayout) findViewById(R.id.menu2);
        menu4 = (LinearLayout) findViewById(R.id.menu4);
        menu5 = (LinearLayout) findViewById(R.id.menu5);

        onChangeBody(1);

        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeBody(1);
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeBody(2);
            }
        });
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeBody(4);
            }
        });
        menu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeBody(5);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main)));



    }


    FragmentTransaction transaction;

    public void onChangeBody(int layoutId) {
        Fragment temp = null;
       // transaction = getSupportFragmentManager().beginTransaction();

        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();


        switch(layoutId){
            case 1:
                mFragment = AllFragment.newInstance();
                mFragmentManager.beginTransaction().replace(R.id.body, mFragment).commit();
                break;
            case 2:
                mFragment = EventFragment.newInstance();
                mFragmentManager.beginTransaction().replace(R.id.body, mFragment).commit();
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, BuyActivity.class));
                break;
            case 4:
                mFragment = WebFragment.newInstance();
                mFragmentManager.beginTransaction().replace(R.id.body, mFragment).commit();
                break;
            case 5:
                mFragment = QFragment.newInstance();
                mFragmentManager.beginTransaction().replace(R.id.body, mFragment).commit();
                break;
        }

       // transaction.addToBackStack(null);
        //getFragmentManager().executePendingTransactions();
       // scListener.onCountChange(getFragmentManager().getBackStackEntryCount());
    }

    public interface onStackCountChangeListener{
        public void onCountChange(int stackCount);
    }
    onStackCountChangeListener scListener;

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        }else{
            getSupportFragmentManager().popBackStack();
            //getFragmentManager().popBackStackImmediate();
            scListener.onCountChange(getSupportFragmentManager().getBackStackEntryCount());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this,BuyActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
