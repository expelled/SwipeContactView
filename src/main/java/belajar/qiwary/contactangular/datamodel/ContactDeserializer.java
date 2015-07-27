package belajar.qiwary.contactangular.datamodel;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import belajar.qiwary.contactangular.helper.Konstanta;

/**
 * Created by hikmat on 26/07/15.
 */
public class ContactDeserializer implements JsonDeserializer<Contact> {
    @Override
    public Contact deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        Konstanta k = new Konstanta();
        final Contact contactObject = new Contact();
        final JsonObject jsonObject = json.getAsJsonObject();

        contactObject.setId(jsonObject.get(k.JSON_id).getAsString());
        contactObject.setName(jsonObject.get(k.JSON_name).getAsString());
        contactObject.setEmail(jsonObject.get(k.JSON_email).getAsString());
        contactObject.setNumber(jsonObject.get(k.JSON_number).getAsString());

        return contactObject;
    }
}
