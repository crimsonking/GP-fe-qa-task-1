package com.ptrukhanovich.gptest.frontend.steps;

import com.ptrukhanovich.gptest.frontend.pages.EventsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;

public class StepDefinitions {
    @Managed
    WebDriver driver;

    EventsPage eventsPage;

    @Given("^I see events page$")
    public void iHaveOpenedUrlHttpSportsWilliamhillComBettingEnGb() throws Throwable {
        eventsPage.open();
    }

    @When("^I open football events$")
    public void iOpenFootballEvents() {
        eventsPage.showFootballEvents();
    }

    @And("^I open first event$")
    public void iOpenFirstEvent() {
        assertThat("There are no events to bet on", eventsPage.haveAnyEvents());
        eventsPage.openEventByOrder(0);
    }

    @Then("^I am able to place a bet$")
    public void iAmAbleToPlaceABet() {
        assertThat("Bet buttons are not enabled", eventsPage.areBetButtonsEnabled());
    }

    @When("^I select \"Home team to win\"$")
    public void iSelectHomeTeamToWin() {
        //We know that the first bet button is for Home team to win, \
        //the second bet button is for Draw, \
        //the third bet button is for Guest team to win.

        eventsPage.selectBetButtonByOrder(0);
    }

    @And("^bet size is \"([^\"]*)\" GBP$")
    public void betSizeInGBP(String betAmount) {
        eventsPage.setAmountForStake(betAmount, 0);
    }

    @Then("^returns are offered$")
    public void oddsAndReturnsAreOffered() {
        assertThat("Returns are not offered", eventsPage.areReturnsOffered());
    }
}
