package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataAssistant;
import ru.netology.data.SQLAssistant;
import ru.netology.page.FrontPanelPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLAssistant.cleanDB;

public class UIAssistantTest {
    FrontPanelPage page = open("http://localhost:8080/", FrontPanelPage.class);
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    public void teardrop() {
        cleanDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // для позитивных сценариев
    @Test
    @DisplayName("Payment by credit card with approved status")
    void shouldBeSuccessPaymentWithCreditCard() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getValidDataForApprovedCard();
        creditPage.inputData(cardInfo);
        creditPage.checkSuccessNotification();
        val actual = SQLAssistant.getStatusCreditRequestEntity();
        assertEquals("APPROVED", actual);
    }

    @Test
    @DisplayName("Payment by credit card with declined status")
    void shouldBeDeclinePaymentWithCreditCard() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getValidDataForDeclinedCard();
        creditPage.inputData(cardInfo);
        creditPage.checkDeclinedNotification();
        val actual = SQLAssistant.getStatusCreditRequestEntity();
        assertEquals("DECLINED", actual);
    }

    // для негативных сценариев с полем "номер карты"
    @Test
    @DisplayName("Payment by credit card with random number")
    void shouldBeDeclinePaymentWithRandomNumber() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithRandomNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkDeclinedNotification();
    }

    @Test
    @DisplayName("Payment by credit card with 15 digits number")
    void shouldBeNotVerificationWith15DigitsNumber() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWith15DigitsNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Payment by credit card with zero in number")
    void shouldBeNotVerificationWithZeroInNumber() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithNumberZero();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();
    }

    // для негативных сценариев с полем "месяц"

    @Test
    @DisplayName("Payment by credit card with 1 digit in month")
    void shouldBeNotVerificationWith1DigitInMonth() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithMonthWith1Digit();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by credit card with 14 digits in month")
    void shouldBeNotVerificationWith14DigitsInMonth() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithMonthMoreThan12();
        creditPage.inputData(cardInfo);
        creditPage.checkInvalidCardExpirationDate();

    }

    @Test
    @DisplayName("Payment by credit card with zero in month")
    void shouldBeNotVerificationWithZeroInMonth() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithMonthWithZero();
        creditPage.inputData(cardInfo);
        creditPage.checkInvalidCardExpirationDate();

    }

    // для негативных сценариев с полем "год"

    @Test
    @DisplayName("Payment by credit card with past year")
    void shouldBeNotVerificationWithPastYear() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithPastYear();
        creditPage.inputData(cardInfo);
        creditPage.checkCardExpired();

    }

    @Test
    @DisplayName("Payment by credit card with year+6")
    void shouldBeNotVerificationWithCurrentYearPlus6() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithFutureYearMoreThan5YearsAhead();
        creditPage.inputData(cardInfo);
        creditPage.checkInvalidCardExpirationDate();

    }

    // для негативных сценариев с полем "владелец"

    @Test
    @DisplayName("Payment by credit card with cyrillic symbols in holder")
    void shouldBeNotVerificationWithCyrillicSymbols() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithCyrillic();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by credit card with just name in holder")
    void shouldBeNotVerificationWithJustName() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithHolderJustWithName();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by credit card with digits in holder")
    void shouldBeNotVerificationWithDigitsInFieldHolder() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithNumbers();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by credit card with spec symbols in holder")
    void shouldBeNotVerificationWithSpecialSymbolInFieldHolder() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithSpecialCharacters();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    // для негативных сценариев с полем "CVC/CVV"

    @Test
    @DisplayName("Payment by credit card with 2 digits in CVC")
    void shouldBeNotVerificationWith2DigitsInCVC() {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithCVCWith2Digits();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Payment by credit card with zero in CVC")
    void shouldBeNotVerificationWithZeroInCVC () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithCVCWithZero();
        creditPage.inputData(cardInfo);
        creditPage.checkWrongFormat();

    }

    // для негативных сценариев с пустыми полями

    @Test
    @DisplayName("Payment by credit card with empty number")
    void shouldBeNotVerificationWithEmptyNumber () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyNumber();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by credit card with empty month")
    void shouldBeNotVerificationWithEmptyMonth () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyMonth();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by credit card with empty year")
    void shouldBeNotVerificationWithEmptyYear () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyYear();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by credit card with empty holder")
    void shouldBeNotVerificationWithEmptyHolder () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyHolder();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by credit card with empty CVC")
    void shouldBeNotVerificationWithEmptyCVC () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyCVC();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by credit card with all empty fields")
    void shouldBeNotVerificationWithEmptyForm () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

}