package com.screenplay.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PetDetailsResponse implements Question<String> {

    private final String field;

    public PetDetailsResponse(String field) {
        this.field = field;
    }

    public static PetDetailsResponse field(String field) {
        return new PetDetailsResponse(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().jsonPath().getString(field);
    }
}
