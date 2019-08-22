package com.example.recyclercardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mlayoutManager;
    private List<Song> songList;
    private SongAdapter songAdapter;

    String[] names = {"I Took A PIll In Ibiza", "7 Years", "Pillow Talk", "Work From Home"
            , "Never Forget You", "Don't Let Me Down", "Love Your Self", "Me, Myself & I", "Cake By the Ocean"
            , "Dangerous Woman", "My House", "Stressed Out", "One Dance", "Middle", "No"};

    String[] singers = {"Mike Posner", "Lukas Graham", "Zayn", "Fifth Harmony",
            "Zara Larsson & MNEK", "The Chainsmokers", "Justin Bieber", "G-Eazy x Bebe Rexha"
            ,"DNCE", "Ariana Grande", "Florida", "Twenty one Pilots", "Drake", "DJ Snake", "Meghan Trainer" };

    int[] pics = {
            R.drawable.took_a_pill,
            R.drawable.seven_years,
            R.drawable.pillow_talk,
            R.drawable.work,
            R.drawable.never_forget_you,
            R.drawable.dont_let_me_down,
            R.drawable.love_yourself,
            R.drawable.me_myself_and_i,
            R.drawable.cake_by_the_ocean,
            R.drawable.dangerous_woman,
            R.drawable.my_house_florida,
            R.drawable.stressed_out,
            R.drawable.one_dance,
            R.drawable.middle,
            R.drawable.no};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        if (mRecyclerView !=null){
            mRecyclerView.setHasFixedSize(true);
        }

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);
        songList = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            Song song = new Song(names[i], singers[i], i + 1, pics[i]);
            songList.add(song);
        }

        songAdapter = new SongAdapter(songList);
        mRecyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(MainActivity.this, "Card at " + position + " is clicked"
                                , Toast.LENGTH_SHORT).show();
                    }
                }));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout_manager, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_grid:
                mlayoutManager = new GridLayoutManager(this, 2);
                mRecyclerView.setLayoutManager(mlayoutManager);
                break;
            case R.id.item_staggered_grid:
                mlayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mlayoutManager);
                break;
            case R.id.item_horizontal:

                mlayoutManager = new
                        LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                mRecyclerView.setLayoutManager(mlayoutManager);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

