package anton.firebasetest.database;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import anton.firebasetest.model.Post;
import anton.firebasetest.model.User;


public final class Snapshot {
    public static List<User> toUsers(DataSnapshot dataSnapshot) {
        ArrayList<User> users = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            User user = snapshot.getValue(User.class);
            user.setKey(snapshot.getKey());
            users.add(user);
        }
        return users;
    }

    public static LinkedHashMap<String, Post> toPosts(DataSnapshot dataSnapshot) {
        LinkedHashMap<String, Post> postList = new LinkedHashMap<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Post post = snapshot.getValue(Post.class);
            postList.put(snapshot.getKey(), post);
        }
        return postList;
    }
}
