package com.example.timeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button myFloBtn = (Button) findViewById(R.id.flobutton);
        myFloBtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          MySetting();
                                      }
                                  }

        );
    }

    public void MySetting(){
        Intent intent=new Intent(this,Setting.class);
        startActivity(intent);

    }


}
