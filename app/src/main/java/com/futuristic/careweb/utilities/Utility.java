package com.futuristic.careweb.utilities;

import android.app.ProgressDialog;
import android.content.Context;

public class Utility {
    private static ProgressDialog progress = null;
    Utility utility;

    public static Utility getInstance(Context context){
        return new Utility(context);
    }
    private Utility(Context context){
        progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
    }

    public void startProgress(){
        if(progress!=null)
          progress.show();
    }
    public void closeProgress(){
        if(progress!=null)
          progress.dismiss();
    }
}
