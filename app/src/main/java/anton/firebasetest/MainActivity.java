package anton.firebasetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;

import anton.firebasetest.model.Note;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_hello_world)
    TextView mHeloWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHeloWorld.setVisibility(View.INVISIBLE);

        Firebase ref = new Firebase("https://docs-examples.firebaseio.com/android/saving-data/fireblog");
        Firebase alanRef = ref.child("users").child("alanisawesome");
        Note note = new Note(, 1912);
        alanRef.setValue(alan);
    }
}
