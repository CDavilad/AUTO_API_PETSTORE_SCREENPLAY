package com.screenplay.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.EnvironmentVariables;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class SetupHook {

    @Before
    public void setTheStage() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = environmentVariables.getProperty("restapi.baseurl", "https://petstore.swagger.io/v2");
        OnStage.setTheStage(Cast.whereEveryoneCan(CallAnApi.at(baseUrl)));
    }

    @After
    public void drawTheCurtain() {
        OnStage.drawTheCurtain();
    }
}
