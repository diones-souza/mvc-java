package controllers;

import models.Person;
import models.PersonRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import views.html.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

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
        return ok(index.render(request,""));
    }

    public CompletionStage<Result> addPerson(final Http.Request request) {
        Person person = formFactory.form(Person.class).bindFromRequest(request).get();
        return personRepository
                .add(person)
                .thenApplyAsync(p -> ok(index.render(request,"Registro Salvo")), ec.current());
    }

    public CompletionStage<Result> getPeople() {
        return personRepository
                .list()
                .thenApplyAsync(p -> ok(list.render(p.collect(Collectors.toList()))), ec.current());
    }

}
