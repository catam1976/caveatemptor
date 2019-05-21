package ro.clovertech.backend.business.impl;

import ro.clovertech.backend.business.CategoryTO;
import ro.clovertech.backend.business.CaveatEmptorException;
import ro.clovertech.backend.business.IService;
import ro.clovertech.backend.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceImpl implements IService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryTO> getCategories() {
        return new ArrayList<>();
    }

    private List<CategoryTO> getRootCategories() {
        Map<Long, Category> allCategories = entityManager
                .createNamedQuery("rootCategories", Category.class)
                .getResultList()
                .stream()
                .collect(
                        Collectors.toMap(Category::getId, categ -> categ)
                );

        return null;
    }

    @Override
    public long createCategory(CategoryTO categoryTO) throws CaveatEmptorException {
        validateCategory(categoryTO);

        Category category = Category.convert(categoryTO);

        return category.getId();
    }

    private void validateCategory(CategoryTO categoryTO) throws CaveatEmptorException {
        if (null == categoryTO.getName() || categoryTO.getName().trim().isEmpty()) {
            throw new CaveatEmptorException("The category name is not defined.", CaveatEmptorException.INVALID_CATEGORY_NAME);
        }

        if (null != categoryTO.getParent()) {
            if (null == categoryTO.getParent().getId()) {
                throw new CaveatEmptorException("The parent category id is not specified.",
                        CaveatEmptorException.INVALID_CATEGORY_PARENT_ID);
            }
        }
    }

}
