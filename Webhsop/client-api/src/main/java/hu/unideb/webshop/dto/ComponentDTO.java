package hu.unideb.webshop.dto;

import java.io.Serializable;

public class ComponentDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer quantity;
    private String comment;
    private RecipeDTO recipeDTO;
    private MaterialDTO materialDTO;

    public ComponentDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RecipeDTO getRecipeDTO() {
        return recipeDTO;
    }

    public void setRecipeDTO(RecipeDTO recipeDTO) {
        this.recipeDTO = recipeDTO;
    }

    public MaterialDTO getMaterialDTO() {
        return materialDTO;
    }

    public void setMaterialDTO(MaterialDTO materialDTO) {
        this.materialDTO = materialDTO;
    }
}
