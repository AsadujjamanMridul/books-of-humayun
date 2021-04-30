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

public class CatagoryShuvro extends AppCompatActivity implements CoverAdapter.SelectedUser {

    List<CustomClass> customList;

    RecyclerView recyclerView;
    String [] bookNames;
    String [] publishYear;
    String [] pageNumber;
    int [] covers = {
            R.drawable.cover_daruchinidip,
            R.drawable.cover_megherchaya,
            R.drawable.cover_rupalidip,
            R.drawable.cover_shuvro,
            R.drawable.cover_eishuvroei,
            R.drawable.cover_shuvrogechebone };

    String [] chapters;
    String [] pos;

    CoverAdapter coverAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_shuvro);

        chapters = getResources().getStringArray(R.array.shuvro_chapters);
        pos = getResources().getStringArray(R.array.position);

        recyclerView = findViewById(R.id.recyclerview);
        bookNames = getResources().getStringArray(R.array.shuvro_book_name);
        publishYear = getResources().getStringArray(R.array.shuvro_publish_year);
        pageNumber = getResources().getStringArray(R.array.shuvro_page_number);

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
        //pairs[1] = new Pair<View, String>(recyclerView, "shared_title");

        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(CatagoryShuvro.this, pairs);
        intent.putExtra("catagory","shuvro");
        intent.putExtra("cover",customList.getCover());
        intent.putExtra("chapter_number",chapters[position]);
        intent.putExtra("book_position",pos[position]);
        intent.putExtra("data",customList);
        startActivity(intent, activityOptions.toBundle());
    }

}
