package co.edu.udea.iw.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import co.edu.udea.iw.dao.CiudadDao;
import co.edu.udea.iw.dto.Ciudad;
import co.edu.udea.iw.exception.MyException;

public class CiudadDaoImp extends HibernateDaoSupport implements CiudadDao {

	@Override
	public List<Ciudad> obtener() throws MyException {
		List<Ciudad> ciudades = new ArrayList<Ciudad>();
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Ciudad.class);
			ciudades = criteria.list();
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

		return ciudades;
	}

	@Override
	public Ciudad obtener(int codigo) throws MyException {
		Ciudad ciudad = new Ciudad();
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Ciudad.class).add(Restrictions.eq("codigo", codigo));
			// ciudad = (Ciudad) session.load(Ciudad.class, codigo); //Si no
			// encuentra el codigo, retorna excepción
			ciudad = (Ciudad) session.get(Ciudad.class, codigo); // Si no
																	// encuentra
																	// el
																	// código,
																	// retorna
																	// un objeto
																	// nulo
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
		return ciudad;
	}

	@Override
	public void guardar(Ciudad ciudad) throws MyException {
		Session session = null;
		try {

			session = getSession();
			Transaction tx = session.beginTransaction();
			session.save(ciudad);
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
	public void actualizar(Ciudad ciudad) throws MyException {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.update(ciudad);
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
	public void eliminar(Ciudad ciudad) throws MyException {
		Session session = null;
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			session.delete(ciudad);
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
