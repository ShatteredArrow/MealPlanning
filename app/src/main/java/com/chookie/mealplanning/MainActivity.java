package com.chookie.mealplanning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = AppCompatActivity.class.getName();
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();

        final ListView listView = (ListView) findViewById(R.id.list);
//        Map<String, Object> shoppingList = new HashMap<>();
//        shoppingList.put("date", sdf.format(date));
//        shoppingList.put("ingredient", Arrays.asList("Brownie", "Tea", "Eggs", "Milk"));


        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();


//        db.collection("shoppingList").document("List1")
//                .set(shoppingList)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(TAG, "DocumentSnapshop successfully added");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d(TAG, "Error writing document", e);
//                    }
//                });


        db.collection("shoppingList")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            final ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

                            for (QueryDocumentSnapshot document : task.getResult()){
                                Map<String, Object> data = document.getData();
                                shoppingLists.add(new ShoppingList( document.getId(), data.get("date").toString(),(ArrayList<String>)data.get("ingredient")));
                            }
                            ShoppingListAdapter adapter = new ShoppingListAdapter(getApplicationContext(), shoppingLists);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Log.d("clicked", shoppingLists.get(position).id);
                                    Intent intent = new Intent(getApplicationContext(), WeeklyList.class);
                                    intent.putExtra("id", shoppingLists.get(position).id);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

//        DocumentReference docRef = db.collection("shoppingList").document("WeeklyList");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()){
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()){
//                        Log.d(TAG, "DocumentSnapshot data: " +document.get("date"));
//                    } else {
//                        Log.d(TAG, "No such Document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });
    }
}
