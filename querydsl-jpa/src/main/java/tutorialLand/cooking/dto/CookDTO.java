package tutorialLand.cooking.dto;

public class CookDTO {
    private long cookUid;
    private String cookName;

    private long chefUid;
    private String chefName;

    public long getCookUid() {
        return cookUid;
    }

    public void setCookUid(long cookUid) {
        this.cookUid = cookUid;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    public long getChefUid() {
        return chefUid;
    }

    public void setChefUid(long chefUid) {
        this.chefUid = chefUid;
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }
}
