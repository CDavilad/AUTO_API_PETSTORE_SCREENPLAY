package com.screenplay.interactions;

import com.screenplay.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.annotations.Step;

import static io.restassured.http.ContentType.JSON;

public class SubmitPetUpdate implements Interaction {

    private final Pet pet;

    public SubmitPetUpdate(Pet pet) {
        this.pet = pet;
    }

    public static SubmitPetUpdate withData(Pet pet) {
        return new SubmitPetUpdate(pet);
    }

    @Override
    @Step("{0} submits updated pet information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/pet").with(request -> request.contentType(JSON).body(pet))
        );
    }
}
