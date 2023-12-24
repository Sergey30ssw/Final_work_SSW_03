package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.gson.Gson;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.APIAssistant;
import ru.netology.data.DataAssistant;
import ru.netology.data.SQLAssistant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLAssistant.cleanDB;

public class APIAssistantTest {
    private static DataAssistant.CardInfo cardInfo;
    private static final Gson gson = new Gson();

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

    @Test
    void shouldBeAnsweredWithStatus200IfApprovedCard() {
        cardInfo = DataAssistant.getValidDataForApprovedCard();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 200, "/api/v1/credit");
        val actual = SQLAssistant.getStatusCreditRequestEntity();
        assertEquals("APPROVED", actual);
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(1, orderCount);

    }

    @Test
    void shouldBeAnsweredWithStatus400IfDeclinedCard() {
        cardInfo = DataAssistant.getValidDataForDeclinedCard();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val actual = SQLAssistant.getStatusCreditRequestEntity();
        assertEquals("DECLINED", actual);
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfFieldNumberIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyNumber();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfFieldMonthIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyMonth();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfFieldYearIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyYear();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfFieldHolderIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyHolder();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfFieldCVCIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyCVC();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

    @Test
    void shouldBeAnsweredWithStatus400IfAllFieldIsEmpty() {
        cardInfo = DataAssistant.getCardInfoWithEmptyFields();
        var body = gson.toJson(cardInfo);
        APIAssistant.createRequest(body, 400, "/api/v1/credit ");
        val orderCount = SQLAssistant.getCountOrderEntity();
        assertEquals(0, orderCount);
    }

}