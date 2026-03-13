package com.screenplay.stepdefinitions;

import com.screenplay.models.Pet;
import com.screenplay.questions.PetDetailsResponse;
import com.screenplay.questions.ResponseCode;
import com.screenplay.tasks.CreatePet;
import com.screenplay.tasks.DeletePet;
import com.screenplay.tasks.GetPet;
import com.screenplay.tasks.UpdatePet;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PetCrudStepDefinitions {

    private Actor actor;
    private Pet pet;

    @Given("el usuario quiere gestionar una mascota en la tienda")
    public void elUsuarioQuiereGestionarUnaMascota() {
        actor = OnStage.theActorCalled("usuario");
    }

    @When("registra una nueva mascota con nombre {string} y estado {string}")
    public void registraUnaNuevaMascota(String nombre, String estado) {
        pet = new Pet();
        pet.id = System.currentTimeMillis() % 1000000;
        pet.name = nombre;
        pet.status = estado;
        pet.photoUrls = List.of("https://example.com/photo.jpg");
        pet.category = new Pet.Category(1L, "Perros");
        pet.tags = List.of(new Pet.Tag(1L, "jugueton"));

        actor.attemptsTo(CreatePet.withData(pet));
    }

    @Then("la mascota es registrada exitosamente")
    public void laMascotaEsRegistradaExitosamente() {
        actor.should(seeThat(ResponseCode.obtained(), equalTo(200)));
        long returnedId = SerenityRest.lastResponse().jsonPath().getLong("id");
        Serenity.setSessionVariable("petId").to(returnedId);
    }

    @When("consulta la informacion de la mascota registrada")
    public void consultaLaInformacionDeLaMascota() {
        long petId = Serenity.sessionVariableCalled("petId");
        actor.attemptsTo(GetPet.withId(petId));
    }

    @Then("obtiene los datos correctos de la mascota")
    public void obtieneLosDataCorrectosDeLaMascota() {
        actor.should(seeThat(ResponseCode.obtained(), equalTo(200)));
        actor.should(seeThat(PetDetailsResponse.field("name"), equalTo("Firulais")));
        actor.should(seeThat(PetDetailsResponse.field("status"), equalTo("available")));
    }

    @When("actualiza el nombre de la mascota a {string} y el estado a {string}")
    public void actualizaLaMascota(String nuevoNombre, String nuevoEstado) {
        long petId = Serenity.sessionVariableCalled("petId");
        pet.id = petId;
        pet.name = nuevoNombre;
        pet.status = nuevoEstado;
        actor.attemptsTo(UpdatePet.withData(pet));
    }

    @Then("la mascota es actualizada exitosamente")
    public void laMascotaEsActualizadaExitosamente() {
        actor.should(seeThat(ResponseCode.obtained(), equalTo(200)));
        actor.should(seeThat(PetDetailsResponse.field("name"), equalTo("Firulais Jr")));
        actor.should(seeThat(PetDetailsResponse.field("status"), equalTo("sold")));
    }

    @When("elimina la mascota de la tienda")
    public void eliminaLaMascotaDeLaTienda() {
        long petId = Serenity.sessionVariableCalled("petId");
        actor.attemptsTo(DeletePet.withId(petId));
    }

    @Then("la mascota es eliminada exitosamente")
    public void laMascotaEsEliminadaExitosamente() {
        actor.should(seeThat(ResponseCode.obtained(), equalTo(200)));
    }
}
