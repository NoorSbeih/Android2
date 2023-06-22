package com.example.splash1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView pdf;
    private RecyclerView recyclerView;
    private StringAdapter adapter;
    private List<Unit> unitList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Unit u1 = new Unit("Unit 1 : New friends\n ",
                "Pages: 4-12\n" +
                "Main ideas : greetings, introducing yourself, saying what you like doing\n\n" +
                "Vocabulary:\n" +
                "Parent: الأبوين\n" +
                "Twin: التوأم\n" +
                "Art: فن\n" +
                "Email: بريد إلكتروني\n" +
                "Internet: شبكة الإتصالات\n" +
                "Work: عمل\n" +
                "Class: فئة\n" +
                "Film: فيلم\n" +
                "Cinema: مسرح\n" +
                "Race: سباق\n\n" +
                "Grammar:\n" +
                "Present simple: I work in london") ;
        Unit u2 = new Unit("Unit 2: Our country\n",
                " pages 12-19\n" +
                "Main ideas: Learning about the present continuous, talking about places\n\n" +
                "Vocabulary:\n" +
                "Mother: الأم\n" +
                "Father: أب\n" +
                "People: الناس\n" +
                "Photo: صورة\n" +
                "Stadium: ملعب\n" +
                "Building: مبنى\n" +
                "Boat: قارب\n" +
                "Cable: سلك\n" +
                "Car: سيارة\n" +
                "Valley: وادي\n" +
                "Famous: مشهور\n" +
                "Hiking: جولة على الأقدام\n" +
                "Wheel: عجلة\n\n" +
                "Grammar:\n" +
                "Present continuous: I am hkikng.");
        Unit u3 = new Unit("Unit 3: Mini-Olympics\n",
                " Pages 20-27\n" +
                "Main ideas: Talking about positions, using ordinal numbers, talking about sports and positions in races\n\n" +
                "Vocabulary:" +
                "\n First: الأول\n" +
                "Second: الثاني\n" +
                "Third: الثالث\n" +
                "Fourth: الرابع\n" +
                "Fifth: الخامس\n" +
                "Metre: متر\n" +
                "Minute: دقيقة\n\n" +
                "Grammar: \n" +
                "1-Adverbs: quickly, slowly, extremely, frequently, daily\n" +
                "example: A turtle walks very slowly\n" +
                "2-Prepositions: on, in, at, beside, across, from.\n" +
                "example: The supermarket is across the street.");
        Unit u4 = new Unit("Unit 4: Holidays in Palestine\n ",
                "Pages 28-35\n" +
                "Main ideas: linking months of the year to seasons, linking weather to seasons\n\n" +
                "Vocabulary:" +
                "January: كانون الثاني\n" +
                "February: شباط\n" +
                "March: آذار\n" +
                "April: نيسان\n" +
                "May: أيار\n" +
                "June: حزيران\n" +
                "July: تموز\n" +
                "August: آب\n" +
                "September: أيلول\n" +
                "October: تشرين الأول\n" +
                "November: تشرين الثاني\n" +
                "December: كانون الاول\n" +
                "Month: شهر\n" +
                "Year: سنة\n");

        pdf = findViewById(R.id.pdftxt);
        pdf.setMovementMethod(LinkMovementMethod.getInstance());

        pdf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Uri uri = Uri.parse("https://wepal.net/edu/uploads/books/ps-books/g5/g5p1/english5p1.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        pdf.setPaintFlags(pdf.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitList = new ArrayList<>();
        unitList.add(u1);
        unitList.add(u2);
        unitList.add(u3);
        unitList.add(u4);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        adapter = new StringAdapter(unitList, sharedPreferences);
        recyclerView.setAdapter(adapter);
    }
}