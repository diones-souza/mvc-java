package domains.person.controllers;

import domains.person.models.Person;
import domains.person.repositories.PersonRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import views.html.person.*;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
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

    public Result index(final Http.Request request, String message) {
        return ok(index.render(request,message));
    }

    public Result edit(final Http.Request request, Long id) {
        return ok(index.render(request,""));
    }

    public CompletionStage<Result> create(final Http.Request request) {
        Person person = formFactory.form(Person.class).bindFromRequest(request).get();
        return personRepository
                .add(person)
                .thenApplyAsync(p -> this.index(request,"Registro salvo"), ec.current());
    }

    public Result update(final Http.Request request) {
        return ok(index.render(request,"teste"));
    }

    public CompletionStage<Result> getPeople() {
        return personRepository
                .list()
                .thenApplyAsync(p -> ok(list.render(p.collect(Collectors.toList()))), ec.current());
    }

}
