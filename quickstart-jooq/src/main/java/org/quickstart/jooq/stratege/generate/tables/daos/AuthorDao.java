/*
 * This file is generated by jOOQ.
 */
package org.quickstart.jooq.stratege.generate.tables.daos;


import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.quickstart.jooq.stratege.generate.tables.Author;
import org.quickstart.jooq.stratege.generate.tables.pojos.AuthorBo;
import org.quickstart.jooq.stratege.generate.tables.records.AuthorRecord;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AuthorDao extends DAOImpl<AuthorRecord, AuthorBo, Integer> {

    /**
     * Create a new AuthorDao without any configuration
     */
    public AuthorDao() {
        super(Author.AUTHOR, AuthorBo.class);
    }

    /**
     * Create a new AuthorDao with an attached configuration
     */
    public AuthorDao(Configuration configuration) {
        super(Author.AUTHOR, AuthorBo.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(AuthorBo object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<AuthorBo> fetchById(Integer... values) {
        return fetch(Author.AUTHOR.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public AuthorBo fetchOneById(Integer value) {
        return fetchOne(Author.AUTHOR.ID, value);
    }

    /**
     * Fetch records that have <code>first_name IN (values)</code>
     */
    public List<AuthorBo> fetchByFirstName(String... values) {
        return fetch(Author.AUTHOR.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>last_name IN (values)</code>
     */
    public List<AuthorBo> fetchByLastName(String... values) {
        return fetch(Author.AUTHOR.LAST_NAME, values);
    }
}
