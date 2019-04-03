package br.com.emersonmendes.jms;

import br.com.emersonmendes.dto.Car;
import br.com.emersonmendes.service.MemberService;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(
        propertyName = "destination",
        propertyValue = "java:/jms/JmsLabQueue"
    ),
    @ActivationConfigProperty(propertyName = "dLQMaxResent", propertyValue = "30")
})
@TransactionManagement(value= TransactionManagementType.CONTAINER)
public class CarQueueMDB implements MessageListener {

    private final static Logger logger = Logger.getLogger(CarQueueMDB.class.toString());

    private static int x1 = 0;
    private static int x2 = 0;

    @Inject
    private MemberService memberService;

    @Override
    public void onMessage(final Message msg) {

        try {

            final ObjectMessage objectMessage = (ObjectMessage) msg;

            final Car car = (Car) objectMessage.getObject();
            memberService.addCar(car);


            if(car.getName().equals("xxx")){
                x1++;
                logger.warning("########################################################################################: " + x1);
                throw new JMSException("xxx");
            }

            if(car.getName().equals("kkk")){
                x2++;
                logger.warning("########################################################################################: " + x2);
                throw new RuntimeException("kkk");
            }

            logger.info(() -> "Received: " + car);

        } catch (JMSException ex) {
            throw new RuntimeException(ex);
        }

    }

}