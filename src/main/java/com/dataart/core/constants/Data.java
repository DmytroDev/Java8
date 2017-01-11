package com.dataart.core.constants;

import com.dataart.core.data.Company;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import com.dataart.core.data.opt.Computer;
import com.dataart.core.data.opt.SoundCard;
import com.dataart.core.data.opt.USB;
import com.dataart.monads.optional.MapNpeProtection;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Data {

    public static final List<String> STRING_LIST = new ArrayList<String>() {{
        add("Buss");
        add("Apple");
        add("Sport");
        add("Symphony");
        add("Alphabet");
        add("Data");
    }};

    public static final String SONG = "Yesterday, all my troubles seemed so far away " +
            "Now it looks as though they're here to stay " +
            "Oh, I believe in yesterday " +
            "Suddenly, I'm not half the man I used to be " +
            "There's a shadow hanging over me. " +
            "Oh, yesterday came suddenly " +
            "Why she had to go I don't know she wouldn't say " +
            "I said something wrong, now I long for yesterday " +
            "Yesterday, love was such an easy game to play sponsored links " +
            "Now I need a place to hide away " +
            "Oh, I believe in yesterday " +
            "Why she had to go I don't know she wouldn't say " +
            "I said something wrong, now I long for yesterday " +
            "Yesterday, love was such an easy game to play " +
            "Now I need a place to hide away " +
            "Oh, I believe in yesterday";

    public static final List<String> WORDS_LIST = Arrays.asList(SONG.toLowerCase().split(" "));


    public static final List<Worker> WORKER_LIST_0 = new ArrayList<Worker>() {{
        add(new Worker(4L, "Alex", "Alexenko", 20, Profession.QA));
        add(new Worker(3L, "Alexander", "Alexendro", 22, Profession.QA));
        add(new Worker(2L, "Petr", "Petrenko", 25, Profession.PROGRAMMER));
        add(new Worker(6L, "Nikola", "Nikolenko", 29, Profession.PROGRAMMER));
        add(new Worker(1L, "Martin", "Martinov", 20, Profession.PROGRAMMER));
        add(new Worker(5L, "Ivan", "Ivanov", 35, Profession.PM));
    }};

    public static final List<Worker> WORKER_LIST_1 = new ArrayList<Worker>() {{
        add(new Worker(1L, "Martin", "Test1", 22, Profession.PROGRAMMER));
        add(new Worker(2L, "Alex", "Test2", 22, Profession.QA));
    }};

    public static final List<Worker> WORKER_LIST_2 = new ArrayList<Worker>() {{
        add(new Worker(3L, "Linus", "Test3", 35, Profession.PROGRAMMER));
        add(new Worker(4L, "Kevin", "Test3", 33, Profession.PM));
    }};

    public static final List<Company> COMPANY_LIST = new ArrayList<Company>() {{
        add(new Company("Test1", Optional.of(WORKER_LIST_1)));
        add(new Company("Test2", Optional.of(WORKER_LIST_2)));
    }};

    public static Optional<Computer> getComputerSoundAsus() {
        Computer computer = new Computer();
        computer.setOs("Linux");
        computer.setPrice(700);
        USB usb = new USB();
        SoundCard soundCard = new SoundCard("‎Asus Xonar U7");

        computer.setUsb(Optional.of(usb));
        usb.setSoundCart(Optional.of(soundCard));
        return Optional.of(computer);
    }

    public static MapNpeProtection<String, Company> getPreraredMap() {
        final MapNpeProtection<String, Company> mapNpeProtection = new MapNpeProtection<>();
        mapNpeProtection.put("Test1", Data.COMPANY_LIST.get(0));
        mapNpeProtection.put("Test2", Data.COMPANY_LIST.get(1));
        return mapNpeProtection;
    }

    public static void sleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
