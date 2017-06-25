package com.pingus.vent.Controller;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pingus.vent.Model.SettingArrayAdapter;
import com.pingus.vent.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        //TextViews
        TextView acct = (TextView) view.findViewById(R.id.textView2);
        TextView notif = (TextView) view.findViewById(R.id.textView3);
        TextView helper = (TextView) view.findViewById(R.id.textView4);

        //ListViews
        ListView account = (ListView) view.findViewById(R.id.settings_account);
        ListView notifications = (ListView) view.findViewById(R.id.settings_notifications);
        ListView help = (ListView) view.findViewById(R.id.settings_contact);

        //ListView Contents
        String[] accountOptions = new String[] {"My Profile", "Blocked Users"};
        String[] notifOptions = new String[] {"Block Comments", "Block Likes", "Block Friend Requests"};
        String[] contactOptions = new String[] {"Contact Us"};

        //ListView Adapters
        ArrayAdapter<String> accountAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, accountOptions);
        account.setAdapter(accountAdapter);
        SettingArrayAdapter notifAdapter = new SettingArrayAdapter(getActivity(), notifOptions);
        notifications.setAdapter(notifAdapter);
        ArrayAdapter<String> contactAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contactOptions);
        help.setAdapter(contactAdapter);

        //Onclick listeners for listviews
        account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position == 0) {
                    Intent intent = new Intent(SettingsFragment.this.getActivity(),ProfileActivity.class);
                    startActivity(intent);
                } else { //position == 1
                    Toast.makeText(SettingsFragment.this.getActivity(), "Clicked on Blocked List!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        help.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(SettingsFragment.this.getActivity(), "Clicked on Contact!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


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
