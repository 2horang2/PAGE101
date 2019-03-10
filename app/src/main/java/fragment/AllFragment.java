package fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapter.FragmentPageAdapter;
import fragment_coffe.All;
import fragment_coffe.Coffee;
import fragment_coffe.Frappuccino;
import fragment_coffe.Smoothie;
import page.page.page101.R;


public class AllFragment extends Fragment {


    TabLayout mTabs;
    ViewPager mViewpager;;
    View v;
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance() {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_all, container, false);
        mTabs = (TabLayout) v.findViewById(R.id.tabs);
        mViewpager = (ViewPager) v.findViewById(R.id.viewpager);

        setupViewPager(mViewpager);
        setupTabLayout(mTabs);




        return v;
    }
    FragmentPageAdapter pageAdapter;
    public void setupViewPager(ViewPager viewPager){
        /// mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());

        Activity ac = (Activity)getActivity();
        FragmentActivity activity = (FragmentActivity) getActivity();

        pageAdapter=new FragmentPageAdapter(ac.getApplicationContext(),activity.getSupportFragmentManager());

        // TextView tab_text = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab_textview,null);
        //   tab_text.setCompoundDrawablesRelativeWithIntrinsicBounds(0,R.drawable.ic_tabbar_chat,0,0);
        pageAdapter.addFragment(All.newInstance(),"ALL");
        pageAdapter.addFragment(Coffee.newInstance(), "커피");
        pageAdapter.addFragment(Smoothie.newInstance(), "스무디");
        pageAdapter.addFragment(Frappuccino.newInstance(), "프라푸치노");
        viewPager.setAdapter(pageAdapter);


    }

    public void setupTabLayout(TabLayout tabLayout){
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(mViewpager);
       // tabLayout.setTabTextColors(getResources().getColorStateList(R.color.main));
        for(int i=0;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab=tabLayout.getTabAt(i);
            tab.setCustomView(pageAdapter.getTabView(i));
        }

        tabLayout.requestFocus();
    }



}
