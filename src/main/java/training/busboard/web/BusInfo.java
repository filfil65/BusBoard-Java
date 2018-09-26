package training.busboard.web;

import java.util.ArrayList;

import training.busboard.Bus;
import training.busboard.BusStop;

public class BusInfo {
    private final String postcode;
    public BusStop stopOne;
    public BusStop stopTwo;
    public ArrayList<Bus> stopOneBusses;
    public ArrayList<Bus> stopTwoBusses;

    public BusInfo(String postcode, BusStop stopOne, ArrayList<Bus> stopOneBusses, BusStop stopTwo, ArrayList<Bus> stopTwoBusses) {
        this.postcode = postcode;
        this.stopOne = stopOne;
        this.stopTwo = stopTwo;
        this.stopOneBusses = stopOneBusses;
        this.stopTwoBusses = stopTwoBusses;
    }

    public String getPostcode() {
        return postcode;
    }
    
    
}
