package br.com.emersonmendes.jms;

import br.com.emersonmendes.dto.Car;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(name = "MyMDB", activationConfig = {
    @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/car"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class CarConsumer implements MessageListener {

    private final static Logger logger = Logger.getLogger(CarConsumer.class.toString());

    @Override
    public void onMessage(final Message msg) {
        final Car car = ((Car) msg);
        logger.info(() -> "Received: " + car);
    }
}