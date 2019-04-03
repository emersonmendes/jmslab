package br.com.emersonmendes.jms;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "java:/jms/JmsLabQueue"
    )
})
public class CarQueueMDB implements MessageListener {

    private final static Logger logger = Logger.getLogger(CarQueueMDB.class.toString());

    @Override
    public void onMessage(final Message msg) {
         logger.info(() -> "Received: " + msg);
    }

}