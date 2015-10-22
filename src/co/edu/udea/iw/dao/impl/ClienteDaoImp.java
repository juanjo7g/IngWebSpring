package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.ClienteDao;
import co.edu.udea.iw.dto.Cliente;
import co.edu.udea.iw.dto.Usuario;
import co.edu.udea.iw.exception.MyException;

public class ClienteDaoImp extends HibernateDaoSupport implements ClienteDao {

	@Override
	public List<Cliente> obtener() throws MyException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Cliente.class);
			clientes = criteria.list();
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					throw new MyException(e);
				}
			}
		}

		return clientes;
	}

	@Override
	public Cliente obtener(String cedula) throws MyException {
		Cliente rol = new Cliente();
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Cliente.class).add(Restrictions.eq("Cedula", cedula));
			rol = (Cliente) session.get(Cliente.class, cedula); // Si no
																// encuentra el
																// código,
																// retorna un
																// objeto nulo
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					throw new MyException(e);
				}
			}
		}
		return rol;
	}

	@Override
	public void guardar(Cliente Cliente) throws MyException {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.save(Cliente);
			tx.commit();
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					throw new MyException(e);
				}
			}
		}
	}

	@Override
	public void actualizar(Cliente Cliente) throws MyException {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(Cliente);
			tx.commit();
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					throw new MyException(e);
				}
			}
		}

	}

	@Override
	public void eliminar(Cliente Cliente) throws MyException {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.delete(Cliente);
			tx.commit();
		} catch (Exception e) {
			throw new MyException(e);
		} finally {
			if (session != null) {
				try {
					session.close();
				} catch (HibernateException e) {
					throw new MyException(e);
				}
			}
		}
	}

}
