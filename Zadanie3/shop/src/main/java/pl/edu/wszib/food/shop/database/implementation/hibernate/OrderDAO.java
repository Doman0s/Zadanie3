package pl.edu.wszib.food.shop.database.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.food.shop.database.IOrderDAO;
import pl.edu.wszib.food.shop.model.Order;

import java.util.List;

@Repository
public class OrderDAO implements IOrderDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(order);
            tr.commit();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Order WHERE user_id = :userId");
        query.setParameter("userId", userId);
        List<Order> result = query.getResultList();
        session.close();
        return result;
    }
}
