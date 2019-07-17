package com.chookie.mealplanning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class WeeklyList extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weeklylist);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("Id", id);

        db.collection("shoppingList").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            ShoppingList currentShoppingList;
                            DocumentSnapshot document = task.getResult();
                            Map<String, Object> data = document.getData();
                            currentShoppingList = (new ShoppingList( document.getId(), data.get("date").toString(),(ArrayList<String>)data.get("ingredient")));


                            TextView name = (TextView) findViewById(R.id.listName);
                            TextView date = (TextView) findViewById(R.id.date);
                            ListView listView = (ListView) findViewById(R.id.ingredient);
//

                            name.setText(currentShoppingList.getId());
                            date.setText(currentShoppingList.getDate());
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, currentShoppingList.ingredients);
                            listView.setAdapter(adapter);


                        } else {
                            Log.d("Weekly", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
