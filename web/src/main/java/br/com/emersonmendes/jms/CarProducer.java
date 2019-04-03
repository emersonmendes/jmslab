package br.com.emersonmendes.jms;

import br.com.emersonmendes.dto.Car;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class CarProducer {

    private final static Logger logger = Logger.getLogger(CarProducer.class.toString());

    @Resource(mappedName = "java:/queue/car")
    Queue testQueue;

    @Inject
    JMSContext jmsContext;

    public void sendMessage(final Car car) {
        logger.info("Sending message: " + car);
        jmsContext.createProducer().send(testQueue, car);
    }

}