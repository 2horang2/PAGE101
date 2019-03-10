package page.page.page101;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fragment.AllFragment;
import fragment.BuyFragment;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        mFragment = BuyFragment.newInstance();
        mFragmentManager.beginTransaction().replace(R.id.body, mFragment).commit();


        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("장바구니");
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main)));
        }
    }


}
