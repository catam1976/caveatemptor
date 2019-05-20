package ro.clovertech.backend.business;

import java.util.List;

public interface IService {
    /**
     * It gets all childs of category identified by parentCategory.
     *
     * @param parentCategory parent category id; it may be null
     *
     * @return child categories
     */
    List<CategoryTO> getCategories(Long parentCategory);
}
