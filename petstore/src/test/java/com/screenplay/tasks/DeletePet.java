package com.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.annotations.Step;

public class DeletePet implements Task {

    private final long petId;

    public DeletePet(long petId) {
        this.petId = petId;
    }

    public static DeletePet withId(long petId) {
        return new DeletePet(petId);
    }

    @Override
    @Step("{0} deletes pet with id {petId}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/pet/{petId}").with(request -> request.pathParam("petId", petId))
        );
    }
}
