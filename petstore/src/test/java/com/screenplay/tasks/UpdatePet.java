package com.screenplay.tasks;

import com.screenplay.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import net.serenitybdd.annotations.Step;

import static io.restassured.http.ContentType.JSON;

public class UpdatePet implements Task {

    private final Pet pet;

    public UpdatePet(Pet pet) {
        this.pet = pet;
    }

    public static UpdatePet withData(Pet pet) {
        return new UpdatePet(pet);
    }

    @Override
    @Step("{0} updates pet information")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/pet").with(request -> request.contentType(JSON).body(pet))
        );
    }
}
