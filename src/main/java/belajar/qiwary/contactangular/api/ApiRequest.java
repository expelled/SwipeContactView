package belajar.qiwary.contactangular.api;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;

import belajar.qiwary.contactangular.datamodel.Contact;
import belajar.qiwary.contactangular.datamodel.ContactDeserializer;


/**
 * Created by hikmat on 26/07/15.
 */
public class ApiRequest {
    /**
     * Returns a dummy object's array
     *
     * @param listener is the listener for the correct answer
     * @param errorListener is the listener for the error response
     *
     */

    public static GsonRequest2<ArrayList<Contact>> getDummyObjectArray
    (
            Response.Listener<ArrayList<Contact>> listener,
            Response.ErrorListener errorListener,
            String url
    )
    {
        final String url2 = url;

        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Contact.class, new ContactDeserializer())
                .create();

        return new GsonRequest2<>
                (
                        url2,
                        new TypeToken<ArrayList<Contact>>() {}.getType(),
                        gson,
                        listener,
                        errorListener
                );
    }


}