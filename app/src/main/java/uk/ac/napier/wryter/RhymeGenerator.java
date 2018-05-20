package uk.ac.napier.wryter;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class RhymeGenerator extends Fragment {

    private TextView mTextView;
    private Button mButton;

    public RhymeGenerator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rhyme_generator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTextView = (TextView) getView().findViewById(R.id.rhyme_text);
        mButton = (Button) getView().findViewById(R.id.rhyme_button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mButton) {
                    new APITask().execute("forgetful");
                }
            }
        });
    }

    class APITask extends AsyncTask<String, Void, String> {

        private ProgressDialog loading = new ProgressDialog(getContext());

        @Override
        protected void onPreExecute() {
            //Loading gif
            loading.setMessage("Loading");
            loading.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String s1 = strings[0];

            //Grabs the text from the API site
            try {
                URL url = new URL("https://api.datamuse.com/words?rel_rhy=" + s1);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            //Stop loading gif
            if (loading.isShowing()){
                loading.dismiss();
            }

            mButton.setVisibility(View.GONE);
            mTextView.setText(s);
        }
    }
}
