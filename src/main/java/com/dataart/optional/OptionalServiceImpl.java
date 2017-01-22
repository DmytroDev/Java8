package com.dataart.optional;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import com.dataart.core.data.opt.Computer;
import com.dataart.core.data.opt.SoundCard;
import com.dataart.core.data.opt.USB;

import java.util.Optional;
import java.util.function.Predicate;

public class OptionalServiceImpl {

    public static void printIfPresent(Optional<Worker> worker) {
        worker.ifPresent(w -> System.out.println(w.getProfession()));
    }

    public static void printOrElse(Optional<Worker> worker) {
        System.out.println(worker.orElse(new Worker(1L, "Default", "Default", 0, Profession.PROGRAMMER)));
    }

    public static Optional<SoundCard> checkSoundCardOrSetDefault(Optional<Computer> computer) {
        return computer.flatMap(Computer::getUsb)
                .map(USB::getSoundCard)
                .orElse(Optional.of(new SoundCard("CH External")));
    }
    // getUsb() returns an Optional as getSoundCard(), so let's use flatMap instead of map
    // And I'd use orElse only in SoundCard creation case

    public static Boolean isSoundCardPresent(Optional<Computer> computer) {
        return computer.flatMap(Computer::getUsb)
                .map(USB::getSoundCard)
                .isPresent();
    }
    // Look at method name - you should check if sound card is present. Try to use the same approach as in the previous method.

    public static String getSoundCardInfo(Optional<Computer> computer) {
        return computer.flatMap(Computer::getUsb)
                .flatMap(USB::getSoundCard)
                .map(SoundCard::getVersion)
                .orElse("NONE");
    }

    public static Optional<Computer> getComputersByCriterias(Optional<Computer> computerOptional, Predicate p) {
        return computerOptional.filter(p);
    }
}
