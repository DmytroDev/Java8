package com.dataart.streams;

import com.dataart.core.constants.Data;
import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamServiceImplTest {

    @Test
    public void testWordsCounter() {
        Long res = StreamServiceImpl.wordsCounter(Data.WORDS_LIST, "yesterday");
        Assert.assertTrue(res == 9);
    }

    @Test
    public void testFindAverageWordsInText() {
        Assert.assertTrue(StreamServiceImpl.getSum(new int[]{1, 2, 3}) == 6);
    }

    @Test
    public void testGetCountWordsLongestThan() {
        StreamServiceImpl.getCountWordsLongestThan(Data.WORDS_LIST, 4);
        Assert.assertTrue(StreamServiceImpl.getCountWordsLongestThan(Data.WORDS_LIST, 4) == 34);
    }

    @Test
    public void testGetCountWordsStartWith() {
        List l1 = new ArrayList<String>() {{
            add("hello");
        }};

        Assert.assertTrue(StreamServiceImpl.getCountWordsStartWith(l1, "he") == 1);
        Assert.assertTrue(StreamServiceImpl.getCountWordsStartWith(l1, "er") == 0);
        Assert.assertTrue(StreamServiceImpl.getCountWordsStartWith(Data.WORDS_LIST, "yes") == 9);
    }

    @Test
    public void testGetFirstWordStartWith() {
        Assert.assertTrue(StreamServiceImpl.getFirstWordStartWith(Data.WORDS_LIST, "lo").equals("looks"));
    }

    @Test
    public void testGetlAverageAge() {
        Assert.assertTrue(StreamServiceImpl.getAverageAge(Data.WORKER_LIST_1) == 22.0);
        Assert.assertTrue(StreamServiceImpl.getAverageAge(Data.WORKER_LIST_2) == 34.0);
    }

    @Test
    public void capitalLetterTest() {
        List<String> res = StreamServiceImpl.capitalLetter(Data.WORDS_LIST);
        res.stream().forEach(w -> Assert.assertTrue(Character.isUpperCase(w.charAt(0))));
    }

    @Test
    public void concatAndToUpperCase() {
        List<String> res = StreamServiceImpl.concatenationAndToUpperCase(Data.WORDS_LIST, Data.WORDS_LIST);
        Assert.assertTrue(res.size() == Data.WORDS_LIST.size() * 2);
        res.forEach(x -> Assert.assertTrue(x.toUpperCase().equals(x)));
    }

    @Test
    public void sortWordsByLengthTest() {
        List<String> res = StreamServiceImpl.sortWordsByLength(Data.WORDS_LIST);
        Assert.assertTrue(res.size() == 128);
        Assert.assertTrue(res.get(0).length() == 1);
        Assert.assertTrue(res.get(10).length() == 1);
        Assert.assertTrue(res.get(20).length() == 2);
        Assert.assertTrue(res.get(30).length() == 2);
        Assert.assertTrue(res.get(40).length() == 3);
        Assert.assertTrue(res.get(50).length() == 3);
        Assert.assertTrue(res.get(60).length() == 3);
        Assert.assertTrue(res.get(70).length() == 4);
        Assert.assertTrue(res.get(80).length() == 4);
        Assert.assertTrue(res.get(90).length() == 4);
        Assert.assertTrue(res.get(100).length() == 6);
    }

    @Test
    public void excludeDuplicateAlphabeticalSortTest() {
        List<String> res = StreamServiceImpl.excludeDuplicateAndAlphabeticalSort(Data.WORDS_LIST);
        Assert.assertTrue(res.size() == 63);
        Assert.assertTrue(res.get(0).equals("a"));
        Assert.assertTrue(res.get(5).equals("be"));
        Assert.assertTrue(res.get(10).equals("far"));
        Assert.assertTrue(res.get(15).equals("half"));
        Assert.assertTrue(res.get(20).equals("i'm"));
        Assert.assertTrue(res.get(25).equals("long"));
        Assert.assertTrue(res.get(30).equals("my"));
        Assert.assertTrue(res.get(50).equals("the"));
        Assert.assertTrue(res.get(60).equals("wrong,"));
    }

    @Test
    public void searchWorkersTest() {
        List<Worker> res1 = StreamServiceImpl.searchWorkers(Data.COMPANY_LIST, Profession.PROGRAMMER);
        Assert.assertTrue(res1.size() == 2);
        Assert.assertTrue(res1.get(0).getProfession() == Profession.PROGRAMMER);
        Assert.assertTrue(res1.get(1).getProfession() == Profession.PROGRAMMER);

        List<Worker> res2 = StreamServiceImpl.searchWorkers(Data.COMPANY_LIST, Profession.QA);
        Assert.assertTrue(res2.size() == 1);
        Assert.assertTrue(res2.get(0).getProfession() == Profession.QA);
    }

}