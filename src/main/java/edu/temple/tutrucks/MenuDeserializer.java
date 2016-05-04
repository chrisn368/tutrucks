/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.temple.tutrucks;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MenuDeserializer implements JsonDeserializer<Menu> {
    @Override
    public Menu deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
      throws JsonParseException {
         final JsonObject jsonObject = json.getAsJsonObject();
         final int id = jsonObject.get("id").getAsInt();
         final String menuName = jsonObject.get("menuName").getAsString();
         final String menuDescription = jsonObject.get("description").getAsString();
         Item[] itemArray = context.deserialize(jsonObject.get("items"), Item[].class);
         Set<Item> itemSet = new HashSet<>(Arrays.asList(itemArray));
         final Menu menu;
         if (id != 0) {
             menu = Menu.getMenubyId(id);
         }
         else {
             menu = new Menu();
             menu.setId(0);
         }
         menu.setDescription(menuDescription);
         menu.setMenuName(menuName);
         menu.setItems(itemSet);
         return menu;
    }
    
}
