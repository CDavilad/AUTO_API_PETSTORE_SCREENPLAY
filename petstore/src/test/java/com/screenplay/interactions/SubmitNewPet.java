package com.screenplay.interactions;

import com.screenplay.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.annotations.Step;

import static io.restassured.http.ContentType.JSON;

public class SubmitNewPet implements Interaction {

    private final Pet pet;

    public SubmitNewPet(Pet pet) {
        this.pet = pet;
    }

    public static SubmitNewPet withData(Pet pet) {
        return new SubmitNewPet(pet);
    }

    @Override
    @Step("{0} submits a new pet to the store")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/pet").with(request -> request.contentType(JSON).body(pet))
        );
    }
}
