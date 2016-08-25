package anton.firebasetest.model;


public class Note {
    private String mTitle;
    private String mContent;
    private String mCreationTime;

    public Note() {
    }

    public Note(String title, String content, String creationTime) {
        mTitle = title;
        mContent = content;
        mCreationTime = creationTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getCreationTime() {
        return mCreationTime;
    }

    public void setCreationTime(String creationTime) {
        mCreationTime = creationTime;
    }
}
