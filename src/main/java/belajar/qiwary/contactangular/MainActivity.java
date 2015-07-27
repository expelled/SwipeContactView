package belajar.qiwary.contactangular;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import belajar.qiwary.contactangular.adapter.SwipeListAdapter;
import belajar.qiwary.contactangular.api.ApiRequest;
import belajar.qiwary.contactangular.api.GsonRequest2;
import belajar.qiwary.contactangular.base.App;
import belajar.qiwary.contactangular.datamodel.Contact;
import belajar.qiwary.contactangular.datamodel.ListContact;
import belajar.qiwary.contactangular.helper.Konstanta;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = MainActivity.class.getSimpleName();

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SwipeListAdapter adapter;
    private List<ListContact> contactList;
    private Konstanta K;

    // initially offset will be 0, later will be updated while parsing the json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        Konstanta K = new Konstanta();

        contactList = new ArrayList<>();
        adapter = new SwipeListAdapter(this, contactList);
        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this); //this ini kl ga salah view

        /**
         * Showing Swipe Refresh animation on activity create
         * As animation won't start on onCreate, post runnable is used
         */
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        loadData();
                                    }
                                }
        );

    }

    /**
     * This method is called when swipe refresh is pulled down
     */
    @Override
    public void onRefresh() {
        loadData();
    }

    /**
     * Fetching movies json by making http call
     */
    private void loadData() {

        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        // appending offset to url
        Log.d(TAG, K.URL_ANGULAR);
        kosong();

        // Volley's json array request object
        final GsonRequest2<ArrayList<Contact>> gsonRequest2 = ApiRequest.getDummyObjectArray
                (
                        new Response.Listener<ArrayList<Contact>>() {
                            @Override
                            public void onResponse(ArrayList<Contact> dummyObjectArrayList) {
                                // Deal with the DummyObject here
                                int size = (dummyObjectArrayList.size()-1);
                                Log.d("MainActivity.class", "jumlah data," + dummyObjectArrayList.size());
                                for (int i = size; i >= 0; i--) {
                                    ListContact m = new ListContact(dummyObjectArrayList.get(i).getId(), String.valueOf(i),
                                            dummyObjectArrayList.get(i).getName(),
                                            dummyObjectArrayList.get(i).getEmail(),
                                            dummyObjectArrayList.get(i).getNumber());
                                    contactList.add(0, m);
                                }
                                adapter.notifyDataSetChanged();
                                // stopping swipe refresh
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }
                        ,
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Deal with the error here
                                // stopping swipe refresh
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        },
                        K.URL_ANGULAR
                );


        // Adding request to request queue
        swipeRefreshLayout.setRefreshing(false);
        App.getInstance().addToRequestQueue(gsonRequest2);

    }

    public void kosong(){
        contactList.clear();
        adapter.notifyDataSetChanged();
    }

}
