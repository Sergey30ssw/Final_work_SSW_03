package ru.netology.page;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.*;

public class FrontPanelPage {

    private SelenideElement payment = $x("//div/button/span/span[text() = 'Купить']");
    private SelenideElement creditRequest = $x("//div/button/span/span[text() = 'Купить в кредит']");

    public TransactionPage clickButtonPayment() {
        payment.click();
        return new TransactionPage();
    }

    public CreditQueryPage clickButtonCredit() {
        creditRequest.click();
        return new CreditQueryPage();
    }
}