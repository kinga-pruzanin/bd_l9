package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "categories", schema = "mac_menu", catalog = "")
public class CategoriesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id")
    private String catId;
    @Basic
    @Column(name = "cat_name")
    private String catName;
    @OneToMany(mappedBy = "category")
    private List<ProductsModel> productsByCatId;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public List<ProductsModel> getProductsByCatId() {
        return productsByCatId;
    }

    public void setProductsByCatId(List<ProductsModel> productsByCatId) {
        this.productsByCatId = productsByCatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriesModel that = (CategoriesModel) o;

        if (catId != null ? !catId.equals(that.catId) : that.catId != null) return false;
        if (catName != null ? !catName.equals(that.catName) : that.catName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = catId != null ? catId.hashCode() : 0;
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        return result;
    }
}
