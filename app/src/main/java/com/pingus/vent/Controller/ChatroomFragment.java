package com.pingus.vent.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pingus.vent.Model.ChatGroup;
import com.pingus.vent.Model.ChatType;
import com.pingus.vent.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatroomFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChatroomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatroomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String name;

    private OnFragmentInteractionListener mListener;

    private DatabaseReference database;

    private ArrayList<ChatGroup> chatItems;

    public ChatroomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatroomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatroomFragment newInstance(String param1, String param2) {
        ChatroomFragment fragment = new ChatroomFragment();
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
        View view = inflater.inflate(R.layout.fragment_chatroom, container, false);
        setHasOptionsMenu(true);
        chatItems = new ArrayList<>();
        //create list of chat rooms
        ListView listView = (ListView) view.findViewById(R.id.listChatRoom);
        final ArrayAdapter<ChatGroup> lvAdapter = new ArrayAdapter<ChatGroup>(
          getActivity(), android.R.layout.simple_list_item_1, chatItems
        );
        listView.setAdapter(lvAdapter);
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header, listView, false);
        listView.addHeaderView(header, "Header", false);

        //list view reacts to item clicks and takes user to new chat room
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChatGroup entry = (ChatGroup) parent.getAdapter().getItem(position);
                if (entry == null) {
                    return;
                }
               Intent nextScreen = new Intent(getActivity(), ChatroomActivity.class);
               nextScreen.putExtra("CHATROOM_NAME", entry.toString());
               startActivity(nextScreen);
         }
        });

        //setup chatroom database
        database = FirebaseDatabase.getInstance().getReference().child("chatroomlist");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<ChatGroup> set = new HashSet<ChatGroup>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    set.add(snapshot.getValue(ChatGroup.class));
                    database.getRoot().push().setValue(snapshot.getValue(ChatGroup.class).toString());
                }
                chatItems.clear();
                chatItems.addAll(set);
                lvAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void addRoom(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
        builder.setTitle("Enter room name");
        final EditText  inputField = new EditText(getActivity());
        builder.setView(inputField);
        builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = inputField.getText().toString().trim();
                ChatGroup newCG = new ChatGroup(name, ChatType.USER_CREATED);
                database.push().setValue(newCG);
                database.getParent().push().setValue(newCG.toString());
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_chatroom_drawer, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        addRoom(getView());
        return true;
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
