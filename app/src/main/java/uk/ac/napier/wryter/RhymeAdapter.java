package uk.ac.napier.wryter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        String rhyme = (String) getItem(position);

        if (rhyme != null) {
            TextView rhymeName = (TextView) convertView.findViewById(R.id.list_rhyme_name);

            rhymeName.setText(rhyme);
        }

        return convertView;
    }
}