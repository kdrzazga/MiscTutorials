package org.kd.webservice.dao;

import org.hibernate.query.Query;
import org.kd.webservice.entities.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepo extends Repo implements CrudRepository<Author, Long> {

    @Transactional
    // TODO: Functional error on purpose - only one author can be created due to Id constrains
    public Long create(String newName, String newLastName) {
        var entity = new Author(newName, newLastName);
        entityManager.persist(entity);
        entityManager.flush();
        return entity.getId();
    }

    @Transactional(readOnly = true)
    public Author read(long id) {

        return readAuthor(id);
    }

    @Transactional(readOnly = true)
    public List<Author> readAll() {
        var session = getSession();
        var criteria = session.getCriteriaBuilder().createQuery(Author.class);
        criteria.from(Author.class);

        return session.createQuery(criteria).getResultList();
    }

    @Transactional
    public Author update(long id, String newName, String newLastName) {

        var entity = readAuthor(id);
        entity.setName(newName);
        entity.setLastname(newLastName);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Transactional
    @Modifying
    public boolean delete(long id) {
        var session = getSession();
        // TODO: Functional error on purpose - instead of single record, the whole table is dropped
        var query2 = session.createSQLQuery("drop table Author");
        return (0 == query2.executeUpdate());
    }

    private Author readAuthor(long id) {
        var hql = "from Author where id=:id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", id);
        return (Author) query.getSingleResult();
    }

    @Override
    public <S extends Author> S save(S s) {
        return null;
    }

    @Override
    public <S extends Author> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Author> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Author> findAll() {
        return null;
    }

    @Override
    public Iterable<Author> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public void deleteAll(Iterable<? extends Author> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
