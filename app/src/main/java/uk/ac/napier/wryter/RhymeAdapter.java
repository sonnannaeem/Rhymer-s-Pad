package uk.ac.napier.wryter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RhymeAdapter extends ArrayAdapter<String> {

    public RhymeAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> rhymes) {
        super(context, resource, rhymes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_rhyme, null);
        }

        final String rhyme = (String) getItem(position);

        //Checks to see if there is a rhyme and then sets it to the appropriate item
        if (rhyme != null) {
            TextView rhymeName = (TextView) convertView.findViewById(R.id.list_rhyme_name);

            rhymeName.setText(rhyme);
        }

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Making toasts display a little above the bottom navigation bar
                Toast toast = Toast.makeText(getContext(), null, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 800);

                //Copying the rhyme to the user's clipboard if they click on it
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text", rhyme);
                clipboard.setPrimaryClip(clip);
                toast.setText("Successfully copied to clipboard!");
                toast.show();
            }
        });

        return convertView;
    }
}