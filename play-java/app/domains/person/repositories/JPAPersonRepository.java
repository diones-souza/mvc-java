package domains.person.repositories;

import domains.person.models.DatabaseExecutionContext;
import domains.person.models.Person;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.io.*;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPAPersonRepository implements PersonRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAPersonRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Person> create(Person person) {
        return supplyAsync(() -> wrap(em -> save(em, person)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Person>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    public CompletionStage<Person> findOnePerson(Long id) {
        return supplyAsync(() -> wrap(em -> findOne(em, id)), executionContext);
    }

    public CompletionStage<Person> destroy(Long id) {
        return supplyAsync(() -> wrap(em -> delete(em, id)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Person save(EntityManager em, Person person) {
        try {
            if (person.id == null) {
                em.persist(person);
            } else {
                if (!em.contains(person)) {
                    if (em.find(Person.class, person.id) == null) {
                        throw new Exception("Erro ao atualizar o registro");
                    }
                }
                person = em.merge(person);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return person;
    }

    private Stream<Person> list(EntityManager em) {
        List<Person> people = em.createQuery("select p from Person p", Person.class).getResultList();
        return people.stream();
    }

    private Person findOne(EntityManager em, Long id) {
        return em.find(Person.class, id);
    }

    private Person delete(EntityManager em, Long id) {
        Person person = findOne(em,id);
        em.remove(person);
        return person;
    }
}
