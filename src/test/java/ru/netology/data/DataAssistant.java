package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataAssistant {
    public DataAssistant() {
    }
    private static Random random = new Random();
    private static Faker faker = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));


    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }
    public static String getPastYear(int minusYears) {
        return LocalDate.now().minusYears(minusYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFutureYearMoreThan5YearsAhead(int plusYears) {
        return LocalDate.now().plusYears(plusYears).format(DateTimeFormatter.ofPattern("yy"));
    }
    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    // для позитивных сценариев с картой, статус которой "Approved"
    public static CardInfo getValidDataForApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), // карта 4444 4444 4444 4441
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    // для позитивных сценариев с картой, статус которой "Declined"
    public static CardInfo getValidDataForDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), // карта 4444 4444 4444 4442
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    // для негативных сценариев с полем "номер карты"
    public static CardInfo getCardInfoWithRandomNumber() {
        return new CardInfo(faker.numerify("1111 2222 3333 4444"), // номер, не совпадающий с номером карты
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    public static CardInfo getCardInfoWith15DigitsNumber() {
        return new CardInfo(faker.numerify("4444 4444 4444 444"), //  номер карты с меньшим количеством цифр (15)
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithNumberZero() {
        return new CardInfo("0000 0000 0000 0000", //  номер карты с номером из шестнадцати нулей
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    // для негативных сценариев с полем "месяц"

    public static CardInfo getCardInfoWithMonthWith1Digit() {
        return new CardInfo(getApprovedCardNumber(),
                faker.numerify("#"), // месяц в виде одной цифры
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithMonthWithZero() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("00")), // месяц св виде двух нулей
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithMonthMoreThan12() {
        return new CardInfo(getApprovedCardNumber(),
                "14", // месяц больше, чем 12
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    // для негативных сценариев с полем "год"

    public static CardInfo getCardInfoWithPastYear() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                getPastYear(1), // прошлый год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithFutureYearMoreThan5YearsAhead() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                getFutureYearMoreThan5YearsAhead(6), //  год из будущего: текущий год + 6 лет
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }

    // для негативных сценариев с полем "владелец"
    public static CardInfo getCardInfoWithHolderJustWithName() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase(), // владелец с именем, но без фамилии
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithHolderWithCyrillic() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                fakerRu.name().firstName().toUpperCase(), // владелец с именем на кириллице
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithHolderWithNumbers() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.numerify("####### ######"), // владелец с цифрами вместо букв
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithHolderWithSpecialCharacters() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                "}{@^#*", // владелец с набором специальных символов вместо букв
                faker.numerify("###")); // CVC
    }

    // для негативных сценариев с полем "CVC"
    public static CardInfo getCardInfoWithCVCWith2Digits() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("##")); // CVC с двумя цифрами вместо трёх
    }
    public static CardInfo getCardInfoWithCVCWithZero() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                "00"); // CVC с нулями
    }

    // для негативных сценариев с пустыми полями
    public static CardInfo getCardInfoWithEmptyNumber() {
        return new CardInfo(null, // пустое (не заполненное) значение номера карты
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithEmptyMonth() {
        return new CardInfo(getApprovedCardNumber(),
                null, // пустое (не заполненное) значение месяца
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithEmptyYear() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                null, // пустое (не заполненное) значение года
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithEmptyHolder() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                null, // пустое (не заполненное) значение владельца
                faker.numerify("###")); // CVC
    }
    public static CardInfo getCardInfoWithEmptyCVC() {
        return new CardInfo(getApprovedCardNumber(),
                LocalDate.now().format(DateTimeFormatter.ofPattern("MM")), // месяц
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")), // год
                faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase(), // владелец
                null); // пустое (не заполненное) значение CVC
    }
    public static CardInfo getCardInfoWithEmptyFields() {
        return new CardInfo(null,
                null, // пустое (не заполненное) значение месяца
                null, // пустое (не заполненное) значение года
                null, // пустое (не заполненное) значение владельца
                null); // пустое (не заполненное) значение CVC
    }



}