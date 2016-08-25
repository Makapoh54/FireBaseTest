package anton.firebasetest.model;


public class Note {
    public String title;
    public String content;
    public String creationTime;

    public Note() {
    }

    public Note(String title, String content, String creationTime) {
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
