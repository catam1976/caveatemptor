package ro.clovertech.backend.business;

import ro.clovertech.backend.model.Category;

import java.util.List;

public class CategoryTO {
    private Long id;
    private String name;

    private CategoryTO parent;

    private List<CategoryTO> subcategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryTO> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryTO> subcategories) {
        this.subcategories = subcategories;
    }

    public CategoryTO getParent() {
        return parent;
    }

    public void setParent(CategoryTO parent) {
        this.parent = parent;
    }

    public static CategoryTO convert(Category category) {
        CategoryTO categoryTO = new CategoryTO();

        categoryTO.setId(category.getId());
        categoryTO.setName(category.getName());

        return categoryTO;
    }

}
