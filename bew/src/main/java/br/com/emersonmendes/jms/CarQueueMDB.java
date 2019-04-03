package br.com.emersonmendes.jms;

import br.com.emersonmendes.dto.Car;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "java:/myJmsTest/MyQueue"
    )
})
public class CarQueueMDB implements MessageListener {

    private final static Logger logger = Logger.getLogger(CarQueueMDB.class.toString());

    @Override
    public void onMessage(final Message msg) {
         logger.info(() -> "Received: " + msg);
    }

}