package com.pingus.vent.Controller;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.pingus.vent.R;

/**
 * Class that controls what a specific user sees on the friend's list
 * Created by Shantanu Mantri on 3/8/2017.
 */
public class FriendlistActivity extends ListActivity {
        String[] itemname ={
                "Darren Leung",
                "Shantanu Mantri",
                "August Wagner",
                "Ishan Waykul",
                "David Mbonu",
                "Mark Zukerberg",
                "Chuck Norris",
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.friendlist_activity);

            this.setListAdapter(new ArrayAdapter<String>(
                    this, R.layout.mylist,
                    R.id.friend_name,itemname));
        }
}
