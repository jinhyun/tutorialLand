package tutorialLand.cooking.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CookMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COOK_MATERIAL_UID")
    private long uid;

    @Column(name = "COOK_QUANTITY")
    private int quantity;

    @Column(name = "MATERIAL_NAME")
    private String materialName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COOK_UID")
    private Cook cook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATERIAL_UID")
    private Material material;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getUid() {
        return uid;
    }

    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public static List<CookMaterial> createCookMaterials(List<Material> materials) {
        List<CookMaterial> cookMaterials = new ArrayList<CookMaterial>();

        for (Material material : materials){
            CookMaterial cookMaterial = new CookMaterial();
            cookMaterial.setMaterialName(material.getName());
            cookMaterial.setMaterial(material);
            cookMaterials.add(cookMaterial);
        }

        return cookMaterials;
    }
}
