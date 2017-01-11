package com.dataart.optional;

import com.dataart.core.data.Profession;
import com.dataart.core.data.Worker;
import com.dataart.core.data.opt.Computer;
import com.dataart.core.data.opt.SoundCard;
import com.dataart.core.data.opt.USB;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OptionalServiceImpl {

    public static void printIfPresent(Optional<Worker> worker) {
        worker.ifPresent(w -> System.out.println(w.getProfession()));
    }

    public static void printOrElse(Optional<Worker> worker) {
        System.out.println(worker.orElse(new Worker(1L, "Default", "Default", 0, Profession.PROGRAMMER)));
    }

    public static Optional<SoundCard> checkSoundCardOrSetDefault(Optional<Computer> computer) {
        return computer.map(Computer::getUsb).orElse(Optional.of(new USB())).map(USB::getSoundCard).orElse(Optional.of(new SoundCard("CH External")));
    }

    public static Boolean isSoundCardPresent(Optional<Computer> computer) {
        return computer.get().getUsb().isPresent();
    }

    public static String getSoundCardInfo(Optional<Computer> computer) {
        return computer.get().getUsb().orElse(new USB()).getSoundCard().orElse(new SoundCard("NONE")).getVersion();
    }

    public static Optional<Computer> getComputersByCriterias(Optional<Computer> computerOptional, Predicate p) {
        return computerOptional.filter(p);
    }
}
