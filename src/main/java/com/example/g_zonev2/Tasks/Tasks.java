package com.example.g_zonev2.Tasks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.g_zonev2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Tasks extends Fragment {

    public List<DynamicRVModel> items = new ArrayList();
    public DynamicRVAdapter dynamicRVAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tasks, container, false);
        final DataSnapshot[] d = new DataSnapshot[1];
        FirebaseDatabase.getInstance().getReference().child("Tasks").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        d[0] =dataSnapshot;

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
        for (DataSnapshot snapshot : d[0].getChildren()) {
            String user = snapshot.child("Name").getValue().toString();
            DynamicRVModel m = new DynamicRVModel(user);
            items.add(m);

        }
        System.out.println("Array size : "+ items.size());
        System.out.println("first elemet: "+items.get(0));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        items.add(new DynamicRVModel("aaa"));
        System.out.println(items.size());


        RecyclerView drv = view.findViewById(R.id.rv_2);
        drv.setLayoutManager(new LinearLayoutManager(getContext()));
        dynamicRVAdapter = new DynamicRVAdapter(drv,this.getActivity(),items);
        drv.setAdapter(dynamicRVAdapter);
        dynamicRVAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=10){
                    dynamicRVAdapter.notifyItemInserted(items.size());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            items.remove(items.size()-1);
                            dynamicRVAdapter.notifyItemRemoved(items.size());

                            int index = items.size();
                            int end = index+10;
                            for(int i=index ; i<end ; i++){

                                String name = UUID.randomUUID().toString();
                                DynamicRVModel item = new DynamicRVModel(name);
                                items.add(item);
                            }
                            dynamicRVAdapter.notifyDataSetChanged();
                            dynamicRVAdapter.setLoaded();

                        }
                    },4000);

                }else
                    Toast.makeText(Tasks.this.getActivity(), "Data completed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}