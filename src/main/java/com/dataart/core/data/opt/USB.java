package com.dataart.core.data.opt;

import java.util.Optional;

public class USB {

    private Optional<SoundCard> soundCard = Optional.empty();

    public Optional<SoundCard> getSoundCard() {
        return soundCard;
    }

    public void setSoundCart(Optional<SoundCard> soundCart) {
        this.soundCard = soundCart;
    }
}
