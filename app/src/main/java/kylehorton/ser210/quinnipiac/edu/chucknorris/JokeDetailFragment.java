package kylehorton.ser210.quinnipiac.edu.chucknorris;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Kyle Horton
 * SER210
 * 4/6/2018
 *
 * This class creates the Fragment used to display the JokeActivity.
 *
 */
public class JokeDetailFragment extends Fragment {


    public JokeDetailFragment() {
        // Required empty public constructor
    }


    // inflates xml layout to a view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke_detail, container, false);
    }

}
