package anton.firebasetest.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import anton.firebasetest.model.Post;
import anton.firebasetest.model.User;
import java8.util.function.Consumer;


public final class FirebaseDb {
    private static final String post = "post";
    private static final String users = "users";
    private static final String creationTime = "creationTime";
    private static final String collection = "collection";
    private static final String assignee = "assignee";

    public static Query getTasksForUser(String assigneeKey) {
        return db().child(post)
                .orderByChild(assignee).equalTo(assigneeKey);
    }

    public static void getUserByKey(String key, Consumer<DataSnapshot> onValue) {
        db().child(users).child(key).addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void getAllPosts(Consumer<DataSnapshot> onValue) {
        db().child(post).orderByChild(creationTime).addValueEventListener(new OnSingleValue(onValue));
    }

    public static void createUser(String uid, User user) {
        db().child(users).child(uid).setValue(user);
    }

    public static void getAllUsers(Consumer<DataSnapshot> onValue) {
        db().child(users).orderByValue().addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void changePostCollection(String draggedTaskKey, String newColumn) {
        db().child(post).child(draggedTaskKey).child(collection).setValue(newColumn);
    }

    public static void createPost(Post newPost) {
        db().child(post).push().setValue(newPost);
    }

    public static void deletePost(String taskKey) {
        db().child(post).child(taskKey).removeValue();
    }

    public static void updatePost(String taskKey, Post newPost) {
        db().child(post).child(taskKey).setValue(newPost);
    }

    public static void getPostByKey(String taskKey, Consumer<DataSnapshot> onValue) {
        db().child(post).child(taskKey).addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void updateUser(String userKey, User user) {
        db().child(users).child(userKey).setValue(user);
    }

    private static DatabaseReference db() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
