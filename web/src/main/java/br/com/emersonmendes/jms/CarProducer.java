package br.com.emersonmendes.jms;

import br.com.emersonmendes.dto.Car;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

import java.util.logging.Logger;

@Stateless
@LocalBean
public class CarProducer {

    private final static Logger logger = Logger.getLogger(CarProducer.class.toString());

    @Resource(lookup = "java:/jms/JmsLabConnectionFactory")
    ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/JmsLabQueue")
    Destination destination;

    public void sendMessage(final Car car) {

        logger.info("Sending message: " + car);

        try (
            QueueConnection connection = (QueueConnection) connectionFactory.createConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination)
        ){
            producer.send(session.createObjectMessage(car));
            logger.info("Message sent! " + car);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

}