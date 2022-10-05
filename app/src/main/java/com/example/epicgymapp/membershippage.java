package com.example.epicgymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class membershippage extends AppCompatActivity {
    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membershippage);

        EditText editText = (EditText)findViewById(R.id.editText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        if(noteID != -1)
        {
            editText.setText(shoppage.notes.get(noteID));
        }

        else
        {
            shoppage.notes.add("");
            noteID = shoppage.notes.size() - 1;
            shoppage.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                shoppage.notes.set(noteID, String.valueOf(s));
                shoppage.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s)
            {

            }
        });
    }
}