package keinerweiss.eventertainer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class HostHomeActivityFragment extends Fragment {

    public HostHomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        View root = inflater.inflate(R.layout.fragment_host_home, container, false);
        TextView txtText = (TextView)root.findViewById(R.id.txtText);
        txtText.setText(text);

        return root;
    }
}
