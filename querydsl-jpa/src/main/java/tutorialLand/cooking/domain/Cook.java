package tutorialLand.cooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COOK_UID")
    private long uid;

    @Column(name = "COOK_NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHEF_UID")
    private Chef chef;

    @OneToMany(mappedBy = "cook", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List <CookMaterial> cookMaterials = new ArrayList<CookMaterial>();

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef) {
        this.chef = chef;
        chef.getCooks().add(this);
    }

    public List<CookMaterial> getCookMaterials() {
        return cookMaterials;
    }

    public void setCookMaterials(List <CookMaterial> cookMaterials) {
        this.cookMaterials = cookMaterials;
    }

    public void addCookMaterials(List <Material> materials) {
        for (Material material : materials) {
            CookMaterial cookMaterial = new CookMaterial();
            cookMaterial.setMaterial(material);
            this.getCookMaterials().add(cookMaterial);
        }
    }

    public void addCookMaterials(CookMaterial cookMaterial) {
        this.getCookMaterials().add(cookMaterial);
    }

    public static Cook createCook(Chef chef, List<CookMaterial> cookMaterials, String cookName) {
        Cook cook = new Cook();
        cook.setChef(chef);
        cook.setName(cookName);

        for (CookMaterial cookMaterial : cookMaterials){
            cookMaterial.setCook(cook);
            cook.addCookMaterials(cookMaterial);
        }

        return cook;
    }
}
