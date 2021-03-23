package com.zoolife.app.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zoolife.app.R;
import com.zoolife.app.Session;
import com.zoolife.app.adapter.MessageAdapter;
import com.zoolife.app.firebase.Collections;
import com.zoolife.app.firebase.models.Group;
import com.zoolife.app.models.MessageModels;

import java.util.ArrayList;
import java.util.List;

import static com.zoolife.app.activity.ChatActivity.TAG;

public class MessageFragment extends Fragment {

    private static final String TAG = "MessageFragment";

    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Group> groupsList;

    private Session session;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MessageFragment(Session session) {
        // Required empty public constructor
        this.session = session;
    }

    /*public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment(ses);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_message, container, false);

        recyclerView = rootview.findViewById(R.id.message_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchGroups();
    }

    private void fetchGroups() {
        if (getActivity() == null) return;
        Collections.groupsRef.whereArrayContains(Group.FRIEND_MODEL, session.getUserId()).get()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documents = task.getResult().getDocuments();
                            Log.d(TAG, "onComplete: document " + documents.toString());

                            groupsList = new ArrayList<>();
                            for (DocumentSnapshot document : documents)
                                groupsList.add(new Group(document));

                            messageAdapter = new MessageAdapter(getActivity(), groupsList, session);
                            messageAdapter.sort();
                            recyclerView.setAdapter(messageAdapter);

                            addGroupSnapshotListener();
                        }
                    }
                });
    }

    private void addGroupSnapshotListener() {
        if (getActivity() == null) return;
        Collections.groupsRef.whereArrayContains(Group.FRIEND_MODEL, session.getUserId()).addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("MessageFragment", error.getMessage());
                    return;
                }

                List<DocumentChange> documentChanges = value.getDocumentChanges();

                for (DocumentChange documentChange : documentChanges) {
                    QueryDocumentSnapshot document = documentChange.getDocument();

                    for (int i = 0; i < groupsList.size(); i++) {
                        Group group = groupsList.get(i);
                        if (group.getGroupId().equals(document.getString(Group.GROUP_ID))) {
                            groupsList.set(i, new Group(document));
                        }

                        messageAdapter.sort();
                        messageAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}