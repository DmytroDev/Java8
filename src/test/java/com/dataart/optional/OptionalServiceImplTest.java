package com.dataart.optional;

import com.dataart.core.constants.Data;
import com.dataart.core.data.Worker;
import com.dataart.core.data.opt.Computer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

public class OptionalServiceImplTest extends TestCase {

    public static Predicate<Computer> predSoundcardPresent() {
        return c -> OptionalServiceImpl.isSoundCardPresent(Optional.of(c));
    }

    public static Predicate<Computer> predCheaperThan(float price) {
        return c -> OptionalServiceImpl.isSoundCardPresent(Optional.of(c));
    }

    @Test
    public void testPrintIfPresent() {
        Optional<Worker> workerOptional = Optional.of(Data.WORKER_LIST_0.get(0));
        OptionalServiceImpl.printIfPresent(workerOptional);
        OptionalServiceImpl.printIfPresent(Optional.empty());
    }

    @Test
    public void testPrintOrElse() {
        Optional<Worker> workerOptional = Optional.of(Data.WORKER_LIST_0.get(0));
        OptionalServiceImpl.printOrElse(workerOptional);
        OptionalServiceImpl.printOrElse(Optional.empty());
    }

    @Test
    public void testGetSoundCardInfo() {
        Assert.assertTrue(OptionalServiceImpl.getSoundCardInfo(Data.getComputerSoundAsus()).equals("‎Asus Xonar U7"));
        Assert.assertTrue(OptionalServiceImpl.getSoundCardInfo(Optional.of(new Computer())).equals("NONE"));
    }

    @Test
    public void testCheckSoundCardOrSetDefault() {
        Assert.assertTrue(OptionalServiceImpl.checkSoundCardOrSetDefault(Optional.of(new Computer())).get().getVersion().equals("CH External"));
        Assert.assertTrue(OptionalServiceImpl.checkSoundCardOrSetDefault(Data.getComputerSoundAsus()).get().getVersion().equals("‎Asus Xonar U7"));
    }

    @Test
    public void testIsSoundCardPresent() {
        Assert.assertFalse(OptionalServiceImpl.isSoundCardPresent(Optional.of(new Computer())));
        Assert.assertTrue(OptionalServiceImpl.isSoundCardPresent(Data.getComputerSoundAsus()));
    }

    @Test
    public void testGetComupersByCriterias() {
        Assert.assertFalse(OptionalServiceImpl.getComputersByCriterias(Optional.of(new Computer()), predSoundcardPresent()).isPresent());
        Assert.assertTrue(OptionalServiceImpl.getComputersByCriterias(Data.getComputerSoundAsus(), predSoundcardPresent()).isPresent());

        Assert.assertFalse(OptionalServiceImpl.getComputersByCriterias(Optional.of(new Computer()), predCheaperThan(100)).isPresent());
        Assert.assertFalse(OptionalServiceImpl.getComputersByCriterias(Optional.of(new Computer()), predCheaperThan(900)).isPresent());
    }


}