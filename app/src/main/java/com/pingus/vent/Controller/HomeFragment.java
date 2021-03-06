package com.pingus.vent.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.pingus.vent.Model.Forismatic;
import com.pingus.vent.Model.QuoteDialog;
import com.pingus.vent.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 * KEY FOR GOOGLE API: AIzaSyBF3I4yb_ORPebhx_8dBOJZFUtaqk_PP_0
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String quote;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView list = (ListView) view.findViewById(R.id.listView1);
        String[] array = getResources().getStringArray(R.array.sections);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, array);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                if(arg2 == 0) { //Quote of the Day
                    RetrieveQuoteTask task = new RetrieveQuoteTask();
                    task.execute();
                    String quote = "";
                    try {
                        quote = task.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SweetAlertDialog dialog = new SweetAlertDialog(HomeFragment.this.getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Quote of the Day!")
                            .setContentText(quote)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            });
                    dialog.show();
                } else if(arg2 == 1) { //Counselor and Psychiatrist
                    toCounsel();
                } else if(arg2 == 2) { //Local Help Centers
                    toMap();
                } else if(arg2 == 3) { //Suicide Prevention Hotlines
                    toHotline();
                } else if(arg2 == 4) { //Quiz
                    toWebView();
                } else if(arg2 == 5) { //FAQ
                    //TODO FAQ PAGE
                    return;
                }
                Log.v("TAG", "CLICKED row number: " + arg2);
            }
        });
        return view;
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

    public void toWebView() {
        Intent intent = new Intent(HomeFragment.this.getActivity(),WebViewActivity.class);
        startActivity(intent);
    }

    public void toMap() {
        Intent intent = new Intent(HomeFragment.this.getActivity(),MapsActivity.class);
        startActivity(intent);
    }

    public void toCounsel() {
        Intent intent = new Intent(HomeFragment.this.getActivity(),CounselingActivity.class);
        startActivity(intent);
    }

    public void toHotline() {
        Intent intent = new Intent(HomeFragment.this.getActivity(),HotlineActivity.class);
        startActivity(intent);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
