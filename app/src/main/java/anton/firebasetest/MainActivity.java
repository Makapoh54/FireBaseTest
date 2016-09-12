package anton.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import anton.firebasetest.adapter.NoteCardViewAdapter;
import anton.firebasetest.database.FirebaseDb;
import anton.firebasetest.model.Post;
import anton.firebasetest.utils.ISO8601DateTime;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static anton.firebasetest.database.Snapshot.toPosts;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.post_title_ed)
    EditText mPostTitle;
    @BindView(R.id.post_content_et)
    EditText mPostContent;

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;

    @OnClick(R.id.create_new_post_button)
    public void createNewPostOnClick() {
        if (!TextUtils.isEmpty(mPostTitle.getText()) && !TextUtils.isEmpty(mPostContent.getText())) {
            createPost(mPostTitle.getText().toString(), mPostContent.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        FirebaseDb.getAllPosts(snapshot -> {
            NoteCardViewAdapter adapter = new NoteCardViewAdapter(this, toPosts(snapshot));
            adapter.SetOnItemClickListener(new NoteCardViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v , int position) {
                    Log.d("TAG", String.valueOf(position));
                }
            });
            mRecyclerView.setAdapter(adapter);
        });
    }

    private void createPost(String title, String content) {
        Post post = new Post(title, content, ISO8601DateTime.now());
        FirebaseDb.createPost(post);
    }

}
