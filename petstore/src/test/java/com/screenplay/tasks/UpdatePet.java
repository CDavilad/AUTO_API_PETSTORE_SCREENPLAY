package com.screenplay.tasks;

import com.screenplay.interactions.SubmitPetUpdate;
import com.screenplay.models.Pet;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.annotations.Step;

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
                SubmitPetUpdate.withData(pet)
        );
    }
}
