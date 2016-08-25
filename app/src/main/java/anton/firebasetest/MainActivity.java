package anton.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import anton.firebasetest.Utils.ISO8601DateTime;
import anton.firebasetest.model.Note;
import anton.firebasetest.model.UserProfile;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.post_title_ed)
    EditText mPostTitle;
    @BindView(R.id.post_content_et)
    EditText mPostContent;

    private DatabaseReference mDatabase;

    @OnClick(R.id.create_profile_button)
    public void createProfileOnClick() {
        if (!TextUtils.isEmpty(mPostTitle.getText()) && !TextUtils.isEmpty(mPostContent.getText())) {
            writeNewUser(mPostTitle.getText().toString(), mPostContent.getText().toString());
        }
    }

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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        attachDataChangedListener();
    }

    private void writeNewUser(String name, String email) {
        UserProfile user = new UserProfile(name, email);
        mDatabase.child("users").child("anton").setValue(user);
    }

    private void createPost(String title, String content) {
        Note note = new Note(title, content, ISO8601DateTime.now());
        mDatabase.child("note").push().setValue(note);
    }

    private void attachDataChangedListener() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Note post = postSnapshot.getValue(Note.class);
                }
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        };
        mDatabase.child("note").addValueEventListener(postListener);
    }
}
