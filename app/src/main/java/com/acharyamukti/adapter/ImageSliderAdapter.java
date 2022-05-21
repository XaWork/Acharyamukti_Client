package com.acharyamukti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.acharyamukti.R;

import java.util.Objects;

public class ImageSliderAdapter extends PagerAdapter {
    Context context;
    int[] image;
    LayoutInflater mLayoutInflater;

    public ImageSliderAdapter(Context context, int[] image) {
        this.context = context;
        this.image = image;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.slider_image, container, false);
        ImageView imageView=view.findViewById(R.id.imageViewMain);
        imageView.setImageResource(image[position]);
        Objects.requireNonNull(container).addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView((LinearLayout) object);

    }
}
