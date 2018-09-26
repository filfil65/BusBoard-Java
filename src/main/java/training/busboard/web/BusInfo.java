package training.busboard.web;

import java.util.ArrayList;

import training.busboard.BusStop;

public class BusInfo {
    private final String postcode;
    public ArrayList<BusStop> stops;

    public BusInfo(String postcode, ArrayList<BusStop> stops) {
        this.postcode = postcode;
        this.stops = stops;
    }

    public String getPostcode() {
        return postcode;
    }
    
    
}
