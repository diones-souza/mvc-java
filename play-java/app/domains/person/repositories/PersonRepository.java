package domains.person.repositories;

import com.google.inject.ImplementedBy;
import domains.person.models.Person;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */
@ImplementedBy(JPAPersonRepository.class)
public interface PersonRepository {

    CompletionStage<Person> create(Person person);

    CompletionStage<Stream<Person>> list();

    CompletionStage<Person> findOnePerson(Long id);

    CompletionStage<Person> destroy(Long id);
}
