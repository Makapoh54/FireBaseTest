package anton.firebasetest;

import android.app.Application;

import com.firebase.client.Firebase;

public class CustomApplication extends Application {

    private static CustomApplication sCustomApplication;

    private Session mSession;

    public static CustomApplication getInstance() {
        return sCustomApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

    public Session getSession() {
        return mSession;
    }

    public void setSession(Session session) {
        mSession = session;
    }

}
