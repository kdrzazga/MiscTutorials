package org.kd.webservice.dao;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

import org.kd.webservice.entities.Book;

@Repository
public class BookRepo extends Repo implements CrudRepository<Book, Long> {

    @Autowired
    private AuthorRepo authorRepo;

    @Transactional
    public long create(Book book) {
        return createBook(book);
    }

    @Transactional(readOnly = true)
    public Book read(long id) {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root).where(builder.equal(root.get("id"), id));//SELECT from book WHERE id=id
        Query<Book> q = session.createQuery(query);
        return q.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<Book> readAll() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Book.class);
        criteria.from(Book.class);

        var books = session.createQuery(criteria).getResultList();
        session.close();
        return books;
    }

    @Transactional
    public Book update(long id, String newTitle, int newPublishYear) {
        // TODO: Functional error on purpose - instead of update, a duplicated record is created,
        //  and authorId cannot be changed - it's hardcoded

        var author = authorRepo.read((long)(1002 + 40 *Math.random()));
        var entity = new Book(author, newPublishYear, newTitle);
        createBook(entity);
        return entity;
    }

    @Transactional
    public boolean delete(long id) {
        //TODO: Functional error on purpose - nothing  deleted here
        return true;
    }

    private long createBook(Book book) {
        entityManager.persist(book);
        getSession().save(book);
        return book.getId();
    }

    @Override
    public <S extends Book> S save(S s) {
        return null;
    }

    @Override
    public <S extends Book> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Iterable<Book> findAllById(Iterable<Long> iterable) {
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
    public void delete(Book book) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
