package domains.person.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import domains.person.models.Person;
import domains.person.repositories.PersonRepository;
import play.data.FormFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import views.html.person.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final FormFactory formFactory;
    private final PersonRepository personRepository;
    private final HttpExecutionContext ec;

    @Inject
    public PersonController(FormFactory formFactory, PersonRepository personRepository, HttpExecutionContext ec) {
        this.formFactory = formFactory;
        this.personRepository = personRepository;
        this.ec = ec;
    }

    public Result index(final Http.Request request) {
        Person person = formFactory.form(Person.class).bindFromRequest(request).get();
        return ok(index.render(request,person));
    }

    public CompletionStage<Result> create(final Http.Request request) {
        Person person = formFactory.form(Person.class).bindFromRequest(request).get();
        return personRepository
                .create(person)
                .thenApplyAsync(p -> ok(Json.toJson(p)), ec.current());
    }

    public CompletionStage<Result> edit(final Http.Request request, Long id) {
        return personRepository
                .findOnePerson(id)
                .thenApplyAsync(p -> ok(index.render(request,p)), ec.current());
    }

    public CompletionStage<Result> getPeople(final Http.Request request) {
        return personRepository
                .list()
                .thenApplyAsync(p -> ok(list.render(request, p.collect(Collectors.toList()))), ec.current());
    }

    public CompletionStage<Result> destroy(final Http.Request request, Long id) {
        return personRepository
                .destroy(id)
                .thenApplyAsync(p -> ok(Json.toJson(p)), ec.current());
    }

}
