package br.com.emersonmendes.service;

import br.com.emersonmendes.dto.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MemberService {

    private final static Logger logger = Logger.getLogger(MemberService.class.toString());

    private static List<Car> myList = new ArrayList<>();

    public List<Car> getMyList() {
        return myList;
    }

    public void addCar(Car car) {
        logger.info("############## addCar ##############");
        this.myList.add(car);
    }

}
