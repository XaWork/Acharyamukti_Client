package com.acharyamukti.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> love = new ArrayList<String>();
        love.add("When will I find my soul mate?");
        love.add("Will I be able to see my long distance partner with me in life?");
        love.add("Will I find my life partner?");
        love.add("How should I make my first move and propose my love of the life?");
        love.add("Will my partner breakup with me?");

        List<String> career = new ArrayList<String>();
        career.add("When will I get my first job");
        career.add("Will I find a job?");
        career.add("What studies should I study to become successful in life?");
        career.add("Will I find my dream job in this year?");
        career.add(" I am very confused in choosing career; will I be able to find a suitable job for better future?");

        List<String> marriage = new ArrayList<String>();
        marriage.add("Will I get married in this year/Month?");
        marriage.add("What will be the perfect Muhurth for my marriage?\n");
        marriage.add("I am not able to find a perfect life partner for my marriage, what should I do?");
        marriage.add("Who will be my life partner and when will I get married to him/her?");
        marriage.add("My parents are very sad because I am not finding a suitable time and day for marriage, what to do?\n");

        List<String> money = new ArrayList<>();
        money.add(" I am not able to save my money, what to do to save my money?");
        money.add("I am having financial problems since a long time and I am having bad times, is there any perfect time\n" +
                "for me to be happy with money?");
        money.add("I had invested in one project and I am not sure I have taken a right decision, what to do?");
        money.add(" I have lost my money in some investment, I have lost my balance in money, what to do?\n");
        money.add("There is an issue with me in spending money, I spend a lot, how can I stop spending money?");

        List<String> business = new ArrayList<>();
        business.add("How should I increase my business?");
        business.add("I am having issues with choosing a business, what business should I chose?");
        business.add("What do I need to explain about my business?");
        business.add("What should I do to be successful in my business?\n");
        business.add("What legal aspects do I need to know about the business?");

        List<String> life = new ArrayList<>();
        life.add("What does my palm say about my future?\n");
        life.add("Which Gemstone is suitable for me?");
        life.add("I want to talk to astrologer, how should I find them?");
        life.add("I am confused with my birth date and time, what to do?");
        life.add("My son is into bad habits, how should I make him a good person?");

        expandableListDetail.put("Love and relationships:\n", love);
        expandableListDetail.put("Career", career);
        expandableListDetail.put("Marriage and Kundali", marriage);
        expandableListDetail.put("Money and Investment", money);
        expandableListDetail.put("Business", business);
        expandableListDetail.put("Everyday life\n", life);
        return expandableListDetail;
    }
}
