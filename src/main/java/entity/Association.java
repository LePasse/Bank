package entity;

public class Association {
    public final int id;
    public final int cardID;
    public final int userID;

    public Association(int id, int cardID, int userID) {
        this.id = id;
        this.cardID = cardID;
        this.userID = userID;
    }
}
