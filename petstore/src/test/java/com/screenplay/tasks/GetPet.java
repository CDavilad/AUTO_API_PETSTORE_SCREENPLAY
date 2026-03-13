package com.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.annotations.Step;

public class GetPet implements Task {

    private final long petId;

    public GetPet(long petId) {
        this.petId = petId;
    }

    public static GetPet withId(long petId) {
        return new GetPet(petId);
    }

    @Override
    @Step("{0} retrieves pet with id {petId}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/{petId}").with(request -> request.pathParam("petId", petId))
        );
    }
}
