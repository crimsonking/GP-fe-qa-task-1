package com.ptrukhanovich.gptest.frontend.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Objects;

@DefaultUrl("http://sports.williamhill.com/betting/en-gb")
public class EventsPage extends PageObject {

    @FindBy(css = "div#nav-football")
    private WebElementFacade footballEventsLabel;

    @FindBy(css = "div.btmarket__link-name")
    private List<WebElementFacade> eventsList;

    @FindBy(css = "button.btn.betbutton")
    private List<WebElementFacade> betButtons;

    @FindBy(css = "input.betslip-selection__stake-input")
    private List<WebElementFacade> stakeInputs;

    @FindBy(css = "#bets-container-singles")
    private WebElementFacade betsContainer;

    @FindBy(css = "input#place-bet-button")
    private WebElementFacade placeBetButton;

    @FindBy(css = "span#total-to-return-price")
    private WebElementFacade returnsLabel;

    private final String EMPTY_RETURNS = "0.00";

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void showFootballEvents() {
        this.footballEventsLabel.waitUntilClickable().click();
    }

    @Step
    public boolean haveAnyEvents() {
        return this.eventsList.size() > 0;
    }

    @Step
    public void openEventByOrder(int eventIndex) {
        this.eventsList.get(eventIndex).
                waitUntilClickable().click();
    }

    @Step
    public boolean areBetButtonsEnabled() {
        return betButtons.size() > 0 && this.betButtons.get(0).waitUntilClickable().isEnabled();
    }

    public void selectBetButtonByOrder(int betButtonIndex) {
        this.betButtons.get(betButtonIndex).waitUntilClickable().click();
        waitFor(this.betsContainer);
    }

    public void setAmountForStake(String betAmount, int stakeIndex) {
        this.stakeInputs.get(stakeIndex).waitUntilClickable()
                .type(betAmount);

        waitFor(placeBetButton).waitUntilEnabled();
    }

    public boolean areReturnsOffered() {
        return this.returnsLabel.isVisible() && !Objects.equals(this.returnsLabel.getText(), EMPTY_RETURNS);
    }
}
