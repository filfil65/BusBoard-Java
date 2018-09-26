package training.busboard.web;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import training.busboard.Bus;
import training.busboard.BusStop;
import training.busboard.Main;

@Controller
@EnableAutoConfiguration
public class Website {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) throws ParseException {
    	
    	// TODO use postcode to get bus data from the Main
		ArrayList<BusStop> stops = Main.main(postcode);
    	
    	BusStop stopOne = stops.get(0);
    	ArrayList<Bus> stopOneBusses = stopOne.getNextFive();
    	
    	BusStop stopTwo = stops.get(1);
    	ArrayList<Bus> stopTwoBusses = stopTwo.getNextFive();

        return new ModelAndView("info", "busInfo", new BusInfo(postcode, stopOne, stopOneBusses, stopTwo, stopTwoBusses)) ;

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}