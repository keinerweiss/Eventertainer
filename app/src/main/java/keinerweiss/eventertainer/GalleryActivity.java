package keinerweiss.eventertainer;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;

public class GalleryActivity extends AppCompatActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.raw.gallery1);
        images.add(R.raw.gallery1);
        images.add(R.raw.gallery3);
        images.add(R.raw.gallery4);
        images.add(R.raw.gallery5);
        images.add(R.raw.gallery6);
        images.add(R.raw.gallery7);
        images.add(R.raw.gallery8);
        images.add(R.raw.gallery9);
        images.add(R.raw.gallery10);
        images.add(R.raw.gallery11);


        mPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter mPagerAdapter = new FullScreenImageAdapter(GalleryActivity.this, images);
        mPager.setAdapter(mPagerAdapter);
    }
}
