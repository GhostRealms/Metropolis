package net.ghostrealms;

import com.google.gson.Gson;
import net.ghostrealms.metropolis.Town;

/**
 * Created by River on 21-Dec-14.
 */
public class Serializer {

    private static Gson gson = new Gson();

    public static String serializeTown(Town town) {
        return gson.toJson(town);
    }

    public static Town deserializeTown(String json) {
        return gson.fromJson(json, Town.class);
    }
}
