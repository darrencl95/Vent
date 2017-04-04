package com.pingus.vent.Model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.pingus.vent.Controller.HomeFragment;
import com.pingus.vent.R;

import java.util.zip.Inflater;

/**
 * Creates a Dialog Box to be used by the "Quote of the Day" Option
 * Created by Shantanu Mantri on 4/3/2017.
 */

public class QuoteDialog extends Dialog implements android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes,no;
    public TextView texter;

    public QuoteDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        texter = (TextView) findViewById(R.id.txt_dia);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        RetrieveQuoteTask task = new RetrieveQuoteTask();
        task.execute();
        try {
            texter.setText(task.get());
        } catch (Exception e) {
            e.printStackTrace();
            texter.setText("No Quote Today...");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                //TODO add to popular vote
                dismiss();
                break;
            case R.id.btn_no:
                //TODO subtract from popular vote
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }


    private class RetrieveQuoteTask extends AsyncTask<String,Void,String> {
        private Exception exeception;
        @Override
        protected String doInBackground(String... urls) {
            try {
                Forismatic.Quote q = new Forismatic(Forismatic.ENGLISH).getQuote();
                return q.getQuoteText();
            } catch (Exception exeception) {
                this.exeception = exeception;
                return "";
            }
        }
        @Override
        protected void onPostExecute(String s) {}
    }
}
