package kylehorton.ser210.quinnipiac.edu.chucknorris;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the ListFragment
 *
 */
public class FavoritesListFragment extends ListFragment {
    private ListView list;

    // interface used to decouple fragments
    static interface Listener{
        void itemClicked(int pos);
    };

    private Listener listener;

    // inflates the ListFragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    // creates the ArrayList adapter to display the ArrayList
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, FavoritesDatabase.listData);
        setListAdapter(adapter);

    }

    // depracated interface method
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.listener = (Listener) activity;
    }

    // what to do when a list item is clicked
    public void onListItemClick(ListView listView, View itemView, int position, long id){
        if (listener != null){
            listener.itemClicked(position);
        }
    }

}
