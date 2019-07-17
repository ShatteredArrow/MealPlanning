package com.chookie.mealplanning;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingList> {
    public ShoppingListAdapter(Context context, ArrayList<ShoppingList> shoppingLists) {
        super(context, R.layout.item_shoppinglist, shoppingLists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_shoppinglist, parent, false);
        }

        // Get the {@link Dessert} object located at this position in the list
        ShoppingList currentShoppingList = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.listName);
        //TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
//        ListView ingredients = (ListView) convertView.findViewById(R.id.ingredient);


        // Get the version name from the current Dessert object and
        // set this text on the name TextView
        nameTextView.setText(currentShoppingList.id);
        //dateTextView.setText(String.valueOf(currentShoppingList.getDate()));
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, currentShoppingList.ingredients);
//        ingredients.setAdapter(adapter);





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
        //
        /*// Get the data item for this position
        ShoppingList shoppingList = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shoppinglist, parent, false);
        }
        // Lookup view for data population
        TextView listName = (TextView) convertView.findViewById(R.id.listName);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        ListView ingredients = (ListView) convertView.findViewById(R.id.ingredient);

        Log.d("shoppingList", shoppingList.ingredients.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, shoppingList.ingredients);

        // Populate the data into the template view using the data object
        listName.setText(shoppingList.name);
        date.setText(shoppingList.date);
        ingredients.setAdapter(adapter);

        // Return the completed view to render on screen
        return convertView;*/
    }
}

