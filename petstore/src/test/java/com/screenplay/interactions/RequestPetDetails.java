package com.screenplay.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.annotations.Step;

public class RequestPetDetails implements Interaction {

    private final long petId;

    public RequestPetDetails(long petId) {
        this.petId = petId;
    }

    public static RequestPetDetails withId(long petId) {
        return new RequestPetDetails(petId);
    }

    @Override
    @Step("{0} requests details for pet with id {petId}")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/{petId}").with(request -> request.pathParam("petId", petId))
        );
    }
}
