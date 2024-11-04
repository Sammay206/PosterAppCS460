package com.example.posterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PosterListener{

    private Button btnAddToWatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecylcerView = findViewById(R.id.posterRecyclerView);
        btnAddToWatchList = findViewById(R.id.btnAddWatchlist);

        List<Poster> posterList = new ArrayList<>();

        Poster avatar = new Poster();
        avatar.image = R.drawable.avatar;
        avatar.name = "Avatar: The Last Airbender";
        avatar.director = "Michael Dante DiMartino";
        avatar.rating = 5f;
        avatar.synopsis = "He's the last one";
        avatar.isSelected = false;
        posterList.add(avatar);

        Poster avengers = new Poster();
        avengers.image = R.drawable.avengers;
        avengers.name = "The Avengers";
        avengers.director = "Joss Whedon";
        avengers.rating = 4f;
        avengers.synopsis = "They fightin!";
        avengers.isSelected = false;
        posterList.add(avengers);

        Poster wallE = new Poster();
        wallE.image = R.drawable.walle;
        wallE.name = "Wall-E";
        wallE.director = "Andrew Stanton";
        wallE.rating = 5f;
        wallE.synopsis = "He's the last one";
        wallE.isSelected = false;
        posterList.add(wallE);

        Poster pussInBoots = new Poster();
        pussInBoots.image = R.drawable.pussinboots;
        pussInBoots.name = "Puss in Boots: The Last Wish";
        pussInBoots.director = "Joel Crawford";
        pussInBoots.rating = 5f;
        pussInBoots.synopsis = "it's his last one";
        pussInBoots.isSelected = false;
        posterList.add(pussInBoots);

        Poster darkKnight = new Poster();
        darkKnight.image = R.drawable.darkknight;
        darkKnight.name = "The Dark Knight";
        darkKnight.director = "Christopher Nolan";
        darkKnight.rating = 5f;
        darkKnight.synopsis = "He's the dark one";
        darkKnight.isSelected = false;
        posterList.add(darkKnight);

        Poster spongebob = new Poster();
        spongebob.image = R.drawable.spongebob;
        spongebob.name = "The Spongebob Squarepants Movie";
        spongebob.director = "Stephen Hillenburg";
        spongebob.rating = 5f;
        spongebob.synopsis = "He's the goofy one";
        spongebob.isSelected = false;
        posterList.add(spongebob);

        Poster shawshank = new Poster();
        shawshank.image = R.drawable.shawshank;
        shawshank.name = "The Shawshank Redemption";
        shawshank.director = "Frank Darabont";
        shawshank.rating = 5f;
        shawshank.synopsis = "He's the only one";
        shawshank.isSelected = false;
        posterList.add(shawshank);

        Poster twelve = new Poster();
        twelve.image = R.drawable.twelvemen;
        twelve.name = "12 Angry Men";
        twelve.director = "Sidney Lumet";
        twelve.rating = 5f;
        twelve.synopsis = "They're MAD!!!";
        twelve.isSelected = false;
        posterList.add(twelve);

        Poster wildRobot = new Poster();
        wildRobot.image = R.drawable.wildrobot;
        wildRobot.name = "The Wild Robot";
        wildRobot.director = "Chris Sanders";
        wildRobot.rating = 4f;
        wildRobot.synopsis = "She's the only one";
        wildRobot.isSelected = false;
        posterList.add(wildRobot);

        Poster barry = new Poster();
        barry.image = R.drawable.barry;
        barry.name = "Barry";
        barry.director = "Bill Hader";
        barry.rating = 5f;
        barry.synopsis = "He's the sus one";
        barry.isSelected = false;
        posterList.add(barry);

        final PosterAdaptor posterAdaptor = new PosterAdaptor(posterList, this);
        postersRecylcerView.setAdapter(posterAdaptor);

        btnAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Poster> selectPosters = posterAdaptor.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for(int i = 0; i < selectPosters.size(); i++){
                    if(i==0){
                        posterNames.append(selectPosters.get(i).name);
                    }else{
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            btnAddToWatchList.setVisibility(View.VISIBLE);
        }else{
            btnAddToWatchList.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}