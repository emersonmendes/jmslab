package br.com.emersonmendes.jms;

import br.com.emersonmendes.model.Member;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

@Stateless
@LocalBean
public class MemberProducer {

    private final static Logger logger = Logger.getLogger(MemberProducer.class.toString());

    @Resource(mappedName = "java:/JmsXA")
    ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/JmsLabQueue")
    Destination destination;

    public void sendMessage(final Member member) {

        logger.info("Sending message: " + member);

        try (
            QueueConnection connection = (QueueConnection) connectionFactory.createConnection();
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination)
        ){

            ObjectMessage data = session.createObjectMessage();
            data.setObject(member);
            producer.setTimeToLive(Message.DEFAULT_TIME_TO_LIVE);
            producer.setPriority(Message.DEFAULT_PRIORITY);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            producer.send(data);

            logger.info("Message sent! " + member);

        } catch (Exception ex){
            throw new RuntimeException("MemberProducer Exception :(");
        }

    }

}