package com.hakimov.product.dao;

import com.hakimov.product.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Product getProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, id);
    }

    @Transactional
    public void save (Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Transactional
    public void update (Product updateProduct, int id) {
        Session session = sessionFactory.getCurrentSession();
        Product productToBeUpdates = session.get(Product.class, id);

        productToBeUpdates.setName(updateProduct.getName());
        productToBeUpdates.setPrice(updateProduct.getPrice());
        productToBeUpdates.setCalories(updateProduct.getCalories());
        productToBeUpdates.setComposition(updateProduct.getComposition());
    }

    @Transactional
    public void delete (int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Product.class, id));
    }
}
