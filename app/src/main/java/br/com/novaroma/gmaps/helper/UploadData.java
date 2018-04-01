package br.com.novaroma.gmaps.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import br.com.novaroma.gmaps.MapsActivity;

/**
 * Created by eduar on 31/03/2018.
 */

public class UploadData extends AsyncTask<String, Integer, String> {

    private ProgressDialog dialog;
    private Context contex;
    private GoogleMap mMap;
    private final static String TAG = "Progress";

    public UploadData(Context contex, GoogleMap mMap){
        this.contex = contex;
        this.mMap = mMap;
    }

    @Override
    protected void onPreExecute() {
        Log.d(TAG, "Pre-execução");
//        super.onPreExecute();
        dialog = ProgressDialog.show(contex, "Por favor aguarde","Carregando dados");
    }

    @Override
    protected String doInBackground(String... strings) {

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);

        if(s != null){

        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
