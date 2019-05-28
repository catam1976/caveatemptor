package ro.clovertech.backend.business.impl;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import ro.clovertech.backend.business.CategoryTO;
import ro.clovertech.backend.business.CaveatEmptorException;
import ro.clovertech.backend.business.IService;
import ro.clovertech.backend.model.Category;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestScoped
public class ServiceImpl implements IService {

    public static final String ERROR_THE_PARENT_CATEGORY_ID_IS_NOT_FOUND_IN_DATABASE = "The parent category id is not found in database.";
    public static final String ERROR_THE_CATEGORY_NAME_IS_NOT_DEFINED = "The category name is not defined.";
    public static final String ERROR_THE_PARENT_CATEGORY_ID_IS_NOT_SPECIFIED = "The parent category id is not specified.";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CategoryTO> getCategories() {
        return new ArrayList<>();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public long createCategory(CategoryTO categoryTO) throws CaveatEmptorException {

        validateCategory(categoryTO);

        Category category = Category.convert(categoryTO);
        category.setId(null);

        if (null != categoryTO.getParent()) {
            Category parentCategory;
            try {
                parentCategory = entityManager.getReference(Category.class, categoryTO.getParent().getId());
            } catch (EntityNotFoundException e) {
                throw new CaveatEmptorException(ERROR_THE_PARENT_CATEGORY_ID_IS_NOT_FOUND_IN_DATABASE,
                        CaveatEmptorException.INVALID_CATEGORY_PARENT_ID);
            }
            category.setParent(parentCategory);
        }

        try {
            entityManager.persist(category);
        } catch (PersistenceException e) {
            throw new CaveatEmptorException(ERROR_THE_PARENT_CATEGORY_ID_IS_NOT_FOUND_IN_DATABASE,
                    CaveatEmptorException.INVALID_CATEGORY_PARENT_ID);
        }

        //TODO: set transaction isolation to 2 #READ_COMMITED (hibernate.connection.isolation in persistcen.xm is not working)

        return category.getId();
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

    private void validateCategory(CategoryTO categoryTO) throws CaveatEmptorException {
        if (null == categoryTO.getName() || categoryTO.getName().trim().isEmpty()) {
            throw new CaveatEmptorException(ERROR_THE_CATEGORY_NAME_IS_NOT_DEFINED, CaveatEmptorException.INVALID_CATEGORY_NAME);
        }

        if (null != categoryTO.getParent()) {
            if (null == categoryTO.getParent().getId()) {
                throw new CaveatEmptorException(ERROR_THE_PARENT_CATEGORY_ID_IS_NOT_SPECIFIED,
                        CaveatEmptorException.INVALID_CATEGORY_PARENT_ID);
            }
        }
    }

}
