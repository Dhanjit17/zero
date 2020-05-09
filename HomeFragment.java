package com.mondaybs.monday.Buyers.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mondaybs.monday.Buyers.adapters.CategoryHomeAdapter;
import com.mondaybs.monday.Buyers.adapters.ViewPagerAdapter;
import com.mondaybs.monday.Buyers.utils.SpacingItemDecoration;
import com.mondaybs.monday.Buyers.utils.Tools;
import com.mondaybs.monday.Model.CategoryHomeModel;
import com.mondaybs.monday.Model.ViewPagerModel;
import com.mondaybs.monday.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor

    }
    private View parent_view;
    private RecyclerView categoryRecyclerView;
    private CategoryHomeAdapter categoryHomeAdapter;

    ////////////// Banner slider

    private ViewPager viewPager;
    private List<ViewPagerModel> viewPagerModelList;
    private ViewPagerAdapter viewPagerAdapter;
    private Runnable runnable = null;
    private Handler handler = new Handler();



    View view;
    ///////////// Banner slider

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.activity_home, container, false);

        parent_view = view.findViewById(R.id.parent_view);




        categoryRecyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getContext(),8),true));

        List<CategoryHomeModel> categoryHomeModelList =new ArrayList<CategoryHomeModel>();
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Ffresh.png?alt=media&token=588923e2-d0ca-49f8-a068-815fc826d8c1","Vegetables & Fruits"));
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Fdaily_essentials.png?alt=media&token=ed3c35fc-381e-4caa-9ffe-f4bdcd98fcbc","Groceries"));
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Frestaurant.png?alt=media&token=17181d8e-2477-45f7-b0f2-94f281981885","Restaurant"));
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Fsweet-food.png?alt=media&token=17ce1f7d-fb7e-479c-9aa7-77dea66a4551","Sweet & Bakeries"));
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Fhealthcare-and-medical.png?alt=media&token=6aafeb07-5be5-4fa0-8a61-304aafa4bac0","Medicine"));
        categoryHomeModelList.add(new CategoryHomeModel("https://firebasestorage.googleapis.com/v0/b/monday-e6b24.appspot.com/o/category_images%2Fhealthcare-and-medical.png?alt=media&token=6aafeb07-5be5-4fa0-8a61-304aafa4bac0h","Fashion"));


    categoryHomeAdapter = new CategoryHomeAdapter(categoryHomeModelList,getContext(),"home_category");

        categoryRecyclerView.setAdapter(categoryHomeAdapter);
        categoryHomeAdapter.notifyDataSetChanged();

        ///////////// Banner slider

        viewPager = view.findViewById(R.id.banner_slider_viewpager);

        viewPagerModelList = new ArrayList<ViewPagerModel>();
        int images[] = {R.drawable.banner_1, R.drawable.banner_2, R.drawable.banner_3};

        for (int i = 0 ; i < images.length ; i++)
        {
            ViewPagerModel viewPagerModel = new ViewPagerModel();
            viewPagerModel.setBanner(images[i]);
            viewPagerModelList.add(viewPagerModel);
        }

        viewPagerAdapter = new ViewPagerAdapter(viewPagerModelList,getContext());
        viewPager.setAdapter(viewPagerAdapter);
        startAutoSlider(viewPagerAdapter.getCount());



        ///////////// Banner slider
        return view;
    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
