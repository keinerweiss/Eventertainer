package keinerweiss.eventertainer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import keinerweiss.eventertainer.GuessGameActivity;

public class GuestHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_home);
        Intent intent = getIntent();
        intent.getStringExtra(Intent.EXTRA_TEXT);
    }

    public void launchGame(View view) {
        Intent guessGameActivity = new Intent(this, GuessGameActivity.class).putExtra(Intent.EXTRA_TEXT, "Übertrag Game");
        startActivity(guessGameActivity);
    }

    public void launchGallery(View view) {
        Intent galleryActivity = new Intent(this, GalleryActivity.class).putExtra(Intent.EXTRA_TEXT, "Übertrag Gallery");
        startActivity(galleryActivity);
    }
}
