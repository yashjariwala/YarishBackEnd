package com.yarish.ecommercebackend.dao.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yarish.ecommercebackend.dao.CartDAO;
import com.yarish.ecommercebackend.dao.UserDAO;
import com.yarish.ecommercebackend.model.Cart;
import com.yarish.ecommercebackend.model.Product;
import com.yarish.ecommercebackend.model.Supplier;
import com.yarish.ecommercebackend.model.UserCustomer;

@Repository("UserDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private CartDAO cartdao;

	@Transactional
	public void save(UserCustomer user) {
		// sessionFactory.getCurrentSession().saveOrUpdate(user);
		Session session = sessionFactory.getCurrentSession();
		 user.setUserrole("ROLE_ADMIN");
		//user.setUserrole("ROLE_USER");
		user.setResettoken(UUID.randomUUID().toString());
		Cart cart = new Cart();
		user.setCart(cart);
		cart.setUsercustomer(user);
		session.saveOrUpdate(user);

	}

	@Transactional
	public void update(UserCustomer user) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = user.getCart();
		user.setCart(cart);
		user.setResettoken(UUID.randomUUID().toString());
		cart.setUsercustomer(user);
		session.saveOrUpdate(user);
	}

	// @Transactional
	// public UserCustomer get(String username) {
	//// String hql = "from UserDetails where id=" + "'" + id + "'";
	//// Query query = (Query)
	// sessionFactory.getCurrentSession().createQuery(hql);
	//// @SuppressWarnings("unchecked")
	//// List<User> list = (List<User>) query.list();
	////
	//// if (list != null && !list.isEmpty()) {
	//// return list.get(0);
	// System.out.println("get1");
	// Session session = this.sessionFactory.getCurrentSession();
	// // Session session = this.sessionFactory.openSession();
	// System.out.println("get2");
	// UserCustomer user = (UserCustomer) session.load(UserCustomer.class, new
	// String(username));
	// System.out.println("get3");
	//// logger.info("USer loaded successfully, User details="+User);
	//// session.flush();
	// // session.close();
	// return user;
	//
	// }
	@Transactional
	public UserCustomer get(String username) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserCustomer where username=?");
		query.setString(0, username);
		UserCustomer user = (UserCustomer) query.uniqueResult();
		return user;

	}

	@Transactional
	public List<UserCustomer> list() {
		@SuppressWarnings("unchecked")
		List<UserCustomer> list = (List<UserCustomer>) sessionFactory.getCurrentSession()
				.createCriteria(UserCustomer.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

		return list;
	}

	@Transactional
	public boolean isValidUser(int id, String name) {
		String hql = "from UserCustomer where id= '" + id + "' and " + " password ='" + name + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<UserCustomer> list = (List<UserCustomer>) query.list();

		if (list != null && !list.isEmpty()) {
			return true;
		}

		return false;
	}

	public UserCustomer get(int userid) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserCustomer where userid=?");
		System.out.println("userid print 1 ");
		query.setLong(0, userid);
		System.out.println("userid print 2 ");
		UserCustomer user = (UserCustomer) query.uniqueResult();
		return user;
	}

	@Transactional
	public void delete(int id) {
		UserCustomer usercustomer = new UserCustomer();
		usercustomer.setUserid(id);
		sessionFactory.getCurrentSession().delete(usercustomer);
	}

	@Transactional
	public void updatedeletecascade(UserCustomer user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Transactional
	public UserCustomer get1(String useremail) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserCustomer where useremail= ?");
		query.setString(0, useremail);
		UserCustomer user = (UserCustomer) query.uniqueResult();
		return user;
	}

	public UserCustomer get2(String resettoken) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from UserCustomer where resettoken= ?");
		query.setString(0, resettoken);
		UserCustomer user = (UserCustomer) query.uniqueResult();
		return user;
	}



}
