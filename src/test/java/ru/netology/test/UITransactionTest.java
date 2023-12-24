package ru.netology.test;

import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataAssistant;
import ru.netology.data.SQLAssistant;
import ru.netology.page.FrontPanelPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLAssistant.cleanDB;

public class UITransactionTest {
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
    @DisplayName("Payment by card with approved status")
    void shouldBeSuccessPaymentWithCreditCard() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getValidDataForApprovedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.checkSuccessNotification();
        val actual = SQLAssistant.getStatusPaymentEntity();
        assertEquals("APPROVED", actual);
    }
    @Test
    @DisplayName("Payment by card with declined status")
    void shouldBeDeclinePaymentWithCreditCard() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getValidDataForDeclinedCard();
        paymentPage.inputData(cardInfo);
        paymentPage.checkDeclinedNotification();
        val actual = SQLAssistant.getStatusPaymentEntity();
        assertEquals("DECLINED", actual);
    }

    // для негативных сценариев с полем "номер карты"
    @Test
    @DisplayName("Payment by card with random number")
    void shouldBeDeclinePaymentWithRandomNumber() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithRandomNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkDeclinedNotification();
    }

    @Test
    @DisplayName("Payment by card with 15 digits number")
    void shouldBeNotVerificationWith15DigitsNumber() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWith15DigitsNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();
    }

    @Test
    @DisplayName("Payment by card with zero in number")
    void shouldBeNotVerificationWithZeroInNumber() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithNumberZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();
    }

    // для негативных сценариев с полем "месяц"

    @Test
    @DisplayName("Payment by card with 1 digit in month")
    void shouldBeNotVerificationWith1DigitInMonth() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithMonthWith1Digit();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by card with 14 digits in month")
    void shouldBeNotVerificationWith14DigitsInMonth() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithMonthMoreThan12();
        paymentPage.inputData(cardInfo);
        paymentPage.checkInvalidCardExpirationDate();

    }

    @Test
    @DisplayName("Payment by card with zero in month")
    void shouldBeNotVerificationWithZeroInMonth() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithMonthWithZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkInvalidCardExpirationDate();

    }

    // для негативных сценариев с полем "год"

    @Test
    @DisplayName("Payment by card with past year")
    void shouldBeNotVerificationWithPastYear() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithPastYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkCardExpired();

    }

    @Test
    @DisplayName("Payment by card with year+6")
    void shouldBeNotVerificationWithCurrentYearPlus6() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithFutureYearMoreThan5YearsAhead();
        paymentPage.inputData(cardInfo);
        paymentPage.checkInvalidCardExpirationDate();

    }

    // для негативных сценариев с полем "владелец"

    @Test
    @DisplayName("Payment by card with cyrillic symbols in holder")
    void shouldBeNotVerificationWithCyrillicSymbols() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithCyrillic();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by card with just name in holder")
    void shouldBeNotVerificationWithJustName() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithHolderJustWithName();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by card with digits in holder")
    void shouldBeNotVerificationWithDigitsInFieldHolder() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithNumbers();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by card with special symbols in holder")
    void shouldBeNotVerificationWithSpecSymbolInFieldHolder() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithHolderWithSpecialCharacters();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }


    // для негативных сценариев с полем "CVC/CVV"

    @Test
    @DisplayName("Payment by card with 2 digits in CVC")
    void shouldBeNotVerificationWith2DigitsInCVC() {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithCVCWith2Digits();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    @Test
    @DisplayName("Payment by card with zero in CVC")
    void shouldBeNotVerificationWithZeroInCVC () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithCVCWithZero();
        paymentPage.inputData(cardInfo);
        paymentPage.checkWrongFormat();

    }

    // для негативных сценариев с пустыми полями

    @Test
    @DisplayName("Payment by card with empty number")
    void shouldBeNotVerificationWithEmptyNumber () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithEmptyNumber();
        paymentPage.inputData(cardInfo);
        paymentPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by card with empty month")
    void shouldBeNotVerificationWithEmptyMonth () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithEmptyMonth();
        paymentPage.inputData(cardInfo);
        paymentPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by card with empty year")
    void shouldBeNotVerificationWithEmptyYear () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithEmptyYear();
        paymentPage.inputData(cardInfo);
        paymentPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by card with empty holder")
    void shouldBeNotVerificationWithEmptyHolder () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithEmptyHolder();
        paymentPage.inputData(cardInfo);
        paymentPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by card with empty CVC")
    void shouldBeNotVerificationWithEmptyCVC () {
        var paymentPage = page.clickButtonPayment();
        var cardInfo = DataAssistant.getCardInfoWithEmptyCVC();
        paymentPage.inputData(cardInfo);
        paymentPage.checkEmptyField();

    }

    @Test
    @DisplayName("Payment by card with empty all fields")
    void shouldBeNotVerificationWithEmptyForm () {
        var creditPage = page.clickButtonCredit();
        var cardInfo = DataAssistant.getCardInfoWithEmptyFields();
        creditPage.inputData(cardInfo);
        creditPage.checkEmptyField();

    }

}