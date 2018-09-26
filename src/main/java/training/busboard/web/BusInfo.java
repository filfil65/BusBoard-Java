package training.busboard.web;

import training.busboard.BusStop;

public class BusInfo {
    private final String postcode;
    public BusStop stopOne;
    public BusStop stopTwo;

    public BusInfo(String postcode, BusStop stopOne, BusStop stopTwo) {
        this.postcode = postcode;
        this.stopOne = stopOne;
        this.stopTwo = stopTwo;
    }

    public String getPostcode() {
        return postcode;
    }
    
    
}
