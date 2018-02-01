package com.example.ahmadrizaldi.traveloka.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ahmadrizaldi.traveloka.R;

/**
 * Created by ahmadrizaldi on 11/6/17.
 */

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_resource = {R.drawable.promo2, R.drawable.promo3, R.drawable.promo4, R.drawable.promo5};
    private Context ctx;
    private LayoutInflater layoutInflater;

    public CustomSwipeAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_resource.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageview = (ImageView) item_view.findViewById(R.id.image_view);
        imageview.setImageResource(image_resource[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {



        container.removeView((LinearLayout)object);
    }

}