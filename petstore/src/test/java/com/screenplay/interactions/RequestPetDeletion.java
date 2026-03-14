package com.screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.annotations.Step;

public class RequestPetDeletion implements Interaction {

    private final long petId;

    public RequestPetDeletion(long petId) {
        this.petId = petId;
    }

    public static RequestPetDeletion withId(long petId) {
        return new RequestPetDeletion(petId);
    }

    @Override
    @Step("{0} requests deletion of pet with id {petId}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.from("/pet/{petId}").with(request -> request.pathParam("petId", petId))
        );
    }
}
