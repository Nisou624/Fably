package com.nisou624.fably;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favorites#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favorites extends Fragment {

    Histoire his;

    ArrayList<Story> favs, histoires;
    RecyclerView fav_stories;
    StoryAdapter sa;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Favorites() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favorites.
     */
    // TODO: Rename and change types and number of parameters
    public static Favorites newInstance(String param1, String param2) {
        Favorites fragment = new Favorites();
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
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        his = (Histoire) this.getActivity().getApplication();
        histoires = new ArrayList<>();
        favs = new ArrayList<>();
        histoires = his.getHistoires();
        StoryUtils su = new StoryUtils(this.getContext());
        for (int i = 0; i < histoires.size(); i++) {
            Story histoire = histoires.get(i);
            if (su.getFavState(String.valueOf(histoire.getId()))) {
                favs.add(histoire);
            }
        }
        fav_stories = view.findViewById(R.id.Stories_list_favorite);
        fav_stories.setLayoutManager(new LinearLayoutManager(getContext()));
        fav_stories.hasFixedSize();

        sa = new StoryAdapter(getContext(), favs);
        sa.setOnFavoriteClickListener(story -> {
            favs.remove(story); // Remove the story from the favorites list
            sa.notifyDataSetChanged(); // Notify adapter about the data change
        });

        fav_stories.setAdapter(sa);
    }
}