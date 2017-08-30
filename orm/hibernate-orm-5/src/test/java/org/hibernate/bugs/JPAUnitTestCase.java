package org.hibernate.bugs;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.bugs.entities.FirstEntity;
import org.hibernate.bugs.entities.FirstEntityId;
import org.hibernate.bugs.entities.SecondEntity;
import org.hibernate.metamodel.internal.EmbeddableTypeImpl;
import org.hibernate.metamodel.internal.MappedSuperclassTypeImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}
	
	@Test
	public void metamodelTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		assertEmbeddedIdPropertyExistsInMetamodel(FirstEntity.class, "firstId");
		assertEmbeddedIdPropertyExistsInMetamodel(SecondEntity.class, "secondId");

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private void assertEmbeddedIdPropertyExistsInMetamodel(Class<?> entityClass, String embeddedIdProperty) {
		EntityType<?> secondEntity = entityManagerFactory.getMetamodel().entity(entityClass);
		EmbeddableType<?> idType = (EmbeddableType<?>) secondEntity.getSupertype().getIdType();
		try {
			Assert.assertNotNull(idType.getAttribute(embeddedIdProperty));
		} catch (IllegalArgumentException e) {
			Assert.fail("Couldn't find expected attribute " + embeddedIdProperty + " in embeddable idType");
		}
	}

	@Test
	public void criteriaBuilderTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		assertCriteriaQueryCanAccessEmbeddedIdProperty(builder, FirstEntity.class, "firstId");
		assertCriteriaQueryCanAccessEmbeddedIdProperty(builder, SecondEntity.class, "secondId");

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	private  <T> void assertCriteriaQueryCanAccessEmbeddedIdProperty(CriteriaBuilder builder, Class<T> entityType, String embeddedIdProperty) {
		CriteriaQuery<T> criteria = builder.createQuery(entityType);
		Root<T> root = criteria.from(entityType);
		criteria.select(root);
		try {
			criteria.where(builder.equal(root.get("id").get(embeddedIdProperty), "abcde"));
		} catch (IllegalArgumentException e) {
			Assert.fail("Couldn't find expected attribute id." + embeddedIdProperty);
		}
		
	}

}
