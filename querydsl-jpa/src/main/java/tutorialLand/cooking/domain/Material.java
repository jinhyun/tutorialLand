package tutorialLand.cooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Material {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIAL_UID")
    private long uid;

    @Column(name = "MATERIAL_NAME")
    private String name;

    @OneToMany(mappedBy = "material", fetch = FetchType.LAZY)
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

    public List<CookMaterial> getCookMaterials() {
        return cookMaterials;
    }

    public void setCookMaterials(List<CookMaterial> cookMaterials) {
        this.cookMaterials = cookMaterials;
    }
}
