package com.mdsl.institution.repository.common;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

/**
 * Entity listener for entities.
 */
@Service
public class MDSLEntityListener
{

    private final Logger log = LoggerFactory.getLogger(MDSLEntityListener.class);

    private static EntityManager entityManager;

    private static EntityManagerFactory entityManagerFactory;

    private static Environment environment;

    /**
     * Executes before an entity is persisted.
     *
     * @param entity The entity being persisted.
     * @throws Exception If an error occurs during pre-persist operations.
     */
    @PrePersist
    public void prePersist(Object entity) throws Exception
    {
	// We can implement some business here and for postPersist and other events like customized auditing
    }

    /**
     * Retrieves the column name for a property of an entity.
     *
     * @param entityClass The class of the entity.
     * @param propertyName The name of the property.
     * @return The column name of the property.
     */
    private String getColumnName(Class<?> entityClass, String propertyName)
    {
	try
	{
	    Field field = entityClass.getDeclaredField(propertyName);
	    Column columnAnnotation = field.getAnnotation(Column.class);
	    if(columnAnnotation != null && columnAnnotation.name() != null)
	    {
		return columnAnnotation.name();
	    }
	}
	catch(NoSuchFieldException e)
	{
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * Retrieves the table name for an entity class.
     *
     * @param entityClass The class of the entity.
     * @return The table name of the entity.
     */
    private String getTableName(Class<?> entityClass)
    {
	Table tableAnnotation = entityClass.getAnnotation(Table.class);
	if(tableAnnotation != null && tableAnnotation.name() != null)
	{
	    return tableAnnotation.name();
	}
	return null;
    }
}
