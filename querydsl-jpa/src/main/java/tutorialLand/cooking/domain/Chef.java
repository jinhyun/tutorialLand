package tutorialLand.cooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chef {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHEF_UID")
    private long uid;

    @Column(name = "CHEF_NAME")
    private String name;

    @OneToMany(mappedBy = "chef")
    private List <Cook> cooks = new ArrayList<Cook>();

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public void setCooks(List<Cook> cooks) {
        this.cooks = cooks;
    }
}
