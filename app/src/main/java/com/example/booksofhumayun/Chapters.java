package com.example.booksofhumayun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksofhumayun.adapter.ChapterListAdapter;
import com.example.booksofhumayun.customclass.CustomClass;

public class Chapters extends AppCompatActivity {

    ImageView sharedCover;
    TextView sharedTitle;

    RecyclerView recyclerView;
    String [] chapters;
    String [] chapter_serial;
    String [] book_name_english;

    ChapterListAdapter chapterListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.chapter_recyclerview);
        sharedCover = findViewById(R.id.sharedCover);
        sharedTitle = findViewById(R.id.sharedTitle);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            int cover = bundle.getInt("cover");
            sharedCover.setImageResource(cover);
        }

        String book_name = null;

        Intent intent = getIntent();
        if(intent.getExtras()!=null)
        {
            CustomClass customClass = (CustomClass) intent.getSerializableExtra("data");
            book_name = customClass.getBookName();
            sharedTitle.setText(book_name);
            getSupportActionBar().setTitle(book_name);
        }


        String catagory = getIntent().getStringExtra("catagory");
        switch (catagory)
        {
            case "himu":
                chapters = getResources().getStringArray(R.array.himu_chapters);
                book_name_english = getResources().getStringArray(R.array.himu_book_name_english);
                break;

            case "shuvro":
                chapters = getResources().getStringArray(R.array.shuvro_chapters);
                book_name_english = getResources().getStringArray(R.array.shuvro_book_name_english);
                break;

            case "misir":
                chapters = getResources().getStringArray(R.array.misir_chapters);
                book_name_english = getResources().getStringArray(R.array.misir_book_name_english);
                break;
        }


        chapter_serial = getResources().getStringArray(R.array.chapter_serial);
        String string =  getIntent().getStringExtra("chapter_number");
        final int chapter_number = Integer.parseInt(string);
        String position = getIntent().getStringExtra("book_position");
        final int book_position = Integer.parseInt(position);

        final String [] x = new String[chapter_number];
        for (int i=0; i<chapter_number; i++)
        {
            x[i] = book_name+" - পর্ব "+chapter_serial[i];
        }

        chapterListAdapter = new ChapterListAdapter(getApplicationContext(), x);
        recyclerView.setAdapter(chapterListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final String book_name_bangla = book_name;

        chapterListAdapter.setOnItemClickListener(new ChapterListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {

                Intent intent = new Intent(getApplicationContext(),FullView.class);
                intent.putExtra("book_name_english",book_name_english[book_position]+(position+1));
                intent.putExtra("title",book_name_bangla);
                intent.putExtra("total_chapters",""+chapter_number);
                intent.putExtra("folderName",book_name_english[book_position]);


                int currentChapterInteger = position+1;
                String currentChapter = ""+currentChapterInteger;


                intent.putExtra("currentChapter",currentChapter);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
