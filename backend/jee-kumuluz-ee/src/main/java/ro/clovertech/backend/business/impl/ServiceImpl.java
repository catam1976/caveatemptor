package ro.clovertech.backend.business.impl;

import ro.clovertech.backend.business.CategoryTO;
import ro.clovertech.backend.business.IService;
import ro.clovertech.backend.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServiceImpl implements IService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryTO> getCategories(Long parentCategory) {
        if (null == parentCategory) {
            return getRootCategories();
        }

        return getChildSubcategoriesOf(parentCategory);
    }

    private List<CategoryTO> getRootCategories() {
        return entityManager
                .createNamedQuery("rootCategories", Category.class)
                .getResultList().stream()
                .map(CategoryTO::convert)
                .collect(Collectors.toList()
                );
    }

    private List<CategoryTO> getChildSubcategoriesOf(Long parentCategory) {
        return entityManager
                .createNamedQuery("subCategories", Category.class)
                .setParameter("categId", parentCategory)
                .getResultList().stream()
                .map(CategoryTO::convert)
                .collect(Collectors.toList()
                );
    }

}
