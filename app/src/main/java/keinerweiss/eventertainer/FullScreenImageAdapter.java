package keinerweiss.eventertainer;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Created by ruedi_000 on 25.08.2016.
 */
public class FullScreenImageAdapter extends PagerAdapter {
    private Activity activity;
    private ArrayList<Integer> imagePaths;
    private LayoutInflater inflater;
    private View viewLayout;
    private ViewGroup container;
    private Integer pos;

    public FullScreenImageAdapter(Activity activity, ArrayList<Integer> imagePaths) {
        this.activity = activity;
        this.imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        this.container = container;
        pos = position;

        AsyncImageLoader loader = new AsyncImageLoader();
        loader.execute(pos);
/*
        View result = null;
        try {
            result = loader.get();
        } catch (Exception e) {
        }
*/
        return viewLayout;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private class AsyncImageLoader extends AsyncTask<Integer, View, View> {
        private Bitmap bitmap;
        private Integer position;

        @Override
        protected View doInBackground(Integer... params) {
            position = params[0];

            InputStream raw = activity.getResources().openRawResource(imagePaths.get(position));
            bitmap = BitmapFactory.decodeStream(raw);

            return viewLayout;
        }

        @Override
        protected void onPostExecute(View result) {
            if(pos == position) {
                ImageView imgDisplay = (ImageView) result.findViewById(R.id.imgDisplay);
                imgDisplay.setImageBitmap(bitmap);
                container.addView(result);
            }
        }

        @Override
        protected void onPreExecute() {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container, false);
        }
    }
}
