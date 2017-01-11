package com.dataart.core.data.opt;

import java.util.Optional;

public class Computer {

    private float price;

    private String os;

    private Optional<USB> usb = Optional.empty();

    public Optional<USB> getUsb() {
        return usb;
    }

    public void setUsb(Optional<USB> usb) {
        this.usb = usb;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }


}
