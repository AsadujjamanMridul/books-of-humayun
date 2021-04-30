package com.example.booksofhumayun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.booksofhumayun.adapter.CoverAdapter;
import com.example.booksofhumayun.customclass.CustomClass;

import java.util.ArrayList;
import java.util.List;

public class CatagoryMisir extends AppCompatActivity implements CoverAdapter.SelectedUser  {

    List<CustomClass> customList;
    RecyclerView recyclerView;

    String [] chapters;
    String [] pos;
    String [] bookNames;
    String [] publishYear;
    String [] pageNumber;
    int [] covers = {
            R.drawable.cover_debi,
            R.drawable.cover_nishithini,
            R.drawable.cover_onnobhubon,
            R.drawable.cover_nishad,
            R.drawable.cover_brihonnola,
            R.drawable.cover_bhoy,
            R.drawable.cover_bipod,
            R.drawable.cover_onish,
            R.drawable.cover_misiraliomimangshitorohoshyo,
            R.drawable.cover_amiebongamra,
            R.drawable.cover_tondrabilash,
            R.drawable.cover_amiemisirali,
            R.drawable.cover_baghbondimisirali,
            R.drawable.cover_hortonishkapon,
            R.drawable.cover_kohenkobikalidas,
            R.drawable.cover_misiralirchoshma,
            R.drawable.cover_misiraliapnikothay,
            R.drawable.cover_misiraliunsolved,
            R.drawable.cover_pufi,
            R.drawable.cover_jokhonnamibeadhar };

    CoverAdapter coverAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_misir);

        chapters = getResources().getStringArray(R.array.misir_chapters);
        pos = getResources().getStringArray(R.array.position);

        recyclerView = findViewById(R.id.recyclerview);
        bookNames = getResources().getStringArray(R.array.misir_book_name);
        publishYear = getResources().getStringArray(R.array.misir_publish_year);
        pageNumber = getResources().getStringArray(R.array.misir_page_number);

        customList  = new ArrayList<>();
        for(int i=0; i<covers.length; i++)
        {
            customList.add(new CustomClass(covers[i],bookNames[i],publishYear[i],pageNumber[i]));
        }

        coverAdapter = new CoverAdapter(customList, this);
        recyclerView.setAdapter(coverAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Book Name, Publish Year, Page Number");
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                coverAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void selectedUser(CustomClass customList) {

        int position=0;
        for(int i=0; i<bookNames.length; i++)
        {
            if(bookNames[i]==customList.getBookName())
            {
                position = i;
                break;
            }
        }

        Intent intent = new Intent(getApplicationContext(), Chapters.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(recyclerView, "shared_cover");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(CatagoryMisir.this, pairs);
        intent.putExtra("cover",customList.getCover());
        intent.putExtra("catagory", "misir");
        intent.putExtra("book_position", pos[position]);
        intent.putExtra("chapter_number", chapters[position]);
        intent.putExtra("data",customList);
        startActivity(intent, activityOptions.toBundle());
    }

}
