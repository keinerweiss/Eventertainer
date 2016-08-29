package keinerweiss.eventertainer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnGuestMode = (Button)findViewById(R.id.btnGuestMode);
        Button btnHostMode = (Button)findViewById(R.id.btnGuestMode);
    }

    public void launchGuest(View view) {
        Intent guestHomeActivity = new Intent(this, GuestHomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Übertrag Guest");
        startActivity(guestHomeActivity);
    }

    public void launchHost(View view) {
        Intent guestHostActivity = new Intent(this, HostHomeActivity.class).putExtra(Intent.EXTRA_TEXT, "Übertrag Host");
        startActivity(guestHostActivity);
    }

    public void launchSettings() {
        Intent settingsActivity = new Intent(this, SettingsActivity.class).putExtra(Intent.EXTRA_TEXT, "Übertrag Settings");
        startActivity(settingsActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_host_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                launchSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void takePicture() {
        //Photo photo = new Photo();
        //photo.dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            mImageView.setImageBitmap(imageBitmap);
        }
        */
    }



    // take photos
    // https://www.youtube.com/watch?v=6Z6k7X2vfhk

    // slide through menu
    // https://www.youtube.com/watch?v=nL0k2usU7I8


}
