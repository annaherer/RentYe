package valuemakers.app.rentye.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class FixedAssetSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Subtype description is required.")
    private String description;

    @ManyToOne
    private FixedAssetType fixedAssetType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FixedAssetType getFixedAssetType() {
        return fixedAssetType;
    }

    public void setFixedAssetType(FixedAssetType fixedAssetType) {
        this.fixedAssetType = fixedAssetType;
    }
}