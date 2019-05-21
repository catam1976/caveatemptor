package ro.clovertech.backend.business;

import java.util.List;

public interface IService {
    /**
     * It gets all childs of category identified by parentCategory.
     *
     * @return child categories
     */
    List<CategoryTO> getCategories();

    /**
     * It creates a category.
     *
     * @param categoryTO the category.
     * @return the new category id
     */
    long createCategory(CategoryTO categoryTO) throws CaveatEmptorException;
}
