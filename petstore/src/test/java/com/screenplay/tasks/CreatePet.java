package com.screenplay.tasks;

import com.screenplay.interactions.SubmitNewPet;
import com.screenplay.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.annotations.Step;

public class CreatePet implements Task {

    private final Pet pet;

    public CreatePet(Pet pet) {
        this.pet = pet;
    }

    public static CreatePet withData(Pet pet) {
        return new CreatePet(pet);
    }

    @Override
    @Step("{0} creates a new pet in the store")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SubmitNewPet.withData(pet)
        );
    }
}
