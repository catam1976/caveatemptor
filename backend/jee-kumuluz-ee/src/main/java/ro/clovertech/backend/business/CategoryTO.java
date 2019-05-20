package ro.clovertech.backend.business;

import ro.clovertech.backend.model.Category;

import java.util.List;

public class CategoryTO {
    private long id;
    private String name;

    private List<CategoryTO> subcategories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public static CategoryTO convert(Category category) {
        CategoryTO categoryTO = new CategoryTO();

        categoryTO.setId(category.getId());
        categoryTO.setName(category.getName());

        return categoryTO;
    }

}
