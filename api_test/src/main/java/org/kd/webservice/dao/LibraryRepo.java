package org.kd.webservice.dao;

import org.hibernate.query.Query;
import org.kd.webservice.entities.ExternalLibrary;
import org.kd.webservice.entities.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class LibraryRepo extends Repo implements CrudRepository<Library, Long> {

    @Transactional
    public Long create(String address) {
        var session = getSession();
        var lib = new Library(address);
        session.save(lib);
        return lib.getId();
    }


    @Transactional
    public List<Library> readAll() {
        //libraries.addAll(readExternalLibraries());
        return readInternalLibraries();
    }

    @Transactional
    public Library update(long id, String newAddress) {
        return updateLibraryAddress(id, newAddress);
    }

    @Override
    public <S extends Library> S save(S s) {
        //TODO
        return null;
    }

    @Override
    public <S extends Library> Iterable<S> saveAll(Iterable<S> iterable) {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public Optional<Library> findById(Long id) {
        return Optional.of(readLibrary(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        //TODO
        return false;
    }

    @Override
    public Iterable<Library> findAll() {
        var libraries = readInternalLibraries();
        //libraries.addAll(readExternalLibraries());
        return libraries;
    }

    @Override
    public Iterable<Library> findAllById(Iterable<Long> iterable) {

        return findAll();
    }

    @Override
    public long count() {
        return readInternalLibraries().size() + readExternalLibraries().size();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        updateLibraryAddress(id, null);
        //TODO: Functional error on purpose - deleting is only resetting fields!
    }

    @Override
    public void delete(Library library) {
        //TODO
    }

    @Override
    public void deleteAll(Iterable<? extends Library> iterable) {
        //TODO
    }

    @Override
    public void deleteAll() {
        //TODO
    }


    private Library updateLibraryAddress(long id, String newAddress) {
        var entity = readLibrary(id);
        entity.setAddress(newAddress);
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    private Library readLibrary(long id) {
        var session = getSession();
        var builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Library> query = builder.createQuery(Library.class);
        Root<Library> root = query.from(Library.class);
        query.select(root).where(builder.equal(root.get("id"), id));
        Query<Library> q = session.createQuery(query);
        return q.getSingleResult();
    }

    private List<Library> readInternalLibraries() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(Library.class);
        criteria.from(Library.class);

        return session.createQuery(criteria).getResultList();
    }

    private List<ExternalLibrary> readExternalLibraries() {
        var session = getSession();
        var builder = session.getCriteriaBuilder();
        var criteria = builder.createQuery(ExternalLibrary.class);
        criteria.from(ExternalLibrary.class);

        return session.createQuery(criteria).getResultList();
    }
}
