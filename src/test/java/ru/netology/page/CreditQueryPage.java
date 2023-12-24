package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataAssistant;
import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;



public class CreditQueryPage {
    private final SelenideElement creditRequestHeading = $x("//div/h3[text() = 'Кредит по данным карты']");
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement cardHolderField = $x("//fieldset/div[3]/span/span[1]/span/span/span[2]/input");
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement operationApproved = $(".notification_status_ok");
    private SelenideElement operationDeclined = $(".notification_status_error");
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private SelenideElement dateWrongFormatError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));
    private SelenideElement continueButton = $x("//div/button/span/span[text() = 'Продолжить']");

    public CreditQueryPage() {
        creditRequestHeading.shouldBe(visible);
    }


    public void inputData(DataAssistant.CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
        continueButton.click();
    }

    public void checkSuccessNotification() {
        operationApproved.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkDeclinedNotification() {
        operationDeclined.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void checkInvalidCardExpirationDate() {
        dateWrongFormatError.shouldBe(Condition.visible);
    }

    public void checkWrongFormat() {
        wrongFormatError.shouldBe(Condition.visible);
    }

    public void checkCardExpired() {
        cardExpiredError.shouldBe(Condition.visible);
    }

    public void checkEmptyField() {
        requiredFieldError.shouldBe(Condition.visible);
    }

}