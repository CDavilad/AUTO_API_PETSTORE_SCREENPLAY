package com.screenplay.tasks;

import com.screenplay.interactions.RequestPetDetails;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
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
                RequestPetDetails.withId(petId)
        );
    }
}
