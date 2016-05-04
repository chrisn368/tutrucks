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
import java.util.List;
import java.util.Set;

public class ItemDeserializer implements JsonDeserializer {

    @Override
    public Item deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
       final JsonObject jsonObject = json.getAsJsonObject();
       final int id = jsonObject.get("id").getAsInt();
       final String name = jsonObject.get("name").getAsString();
       final double price = jsonObject.get("price").getAsDouble();
       final String tagString = jsonObject.get("tags").getAsString();
       List<String> tagArray = Arrays.asList(tagString.split("\\s*,\\s*"));
       Set<Tag> tagSet = new HashSet<>();
       for (String s: tagArray) {
           Tag t = Tag.retrieveTag(s, true);
           tagSet.add(t);
       }
       final Item item;
       if (id != 0) {
           item = Item.getItemByID(id);
       }
       else {
           item = new Item();
       }
       item.setItemName(name);
       item.setPrice(price);
       item.setTags(tagSet);
       return item;
    }

}
