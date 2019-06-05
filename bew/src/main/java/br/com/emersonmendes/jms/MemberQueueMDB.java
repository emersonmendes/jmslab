package br.com.emersonmendes.jms;

import br.com.emersonmendes.model.Member;
import br.com.emersonmendes.service.MemberService;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty( propertyName = "destination", propertyValue = "java:/jms/JmsLabQueue")
})
public class MemberQueueMDB implements MessageListener {

    private final static Logger logger = Logger.getLogger(MemberQueueMDB.class.toString());

    private static int x1 = 0;
    private static int x2 = 0;

    @Inject
    private MemberService memberService;

    @Override
    public void onMessage(final Message msg) {

        try {

            final ObjectMessage objectMessage = (ObjectMessage) msg;

            final Member member = (Member) objectMessage.getObject();
            memberService.addMember(member);

            if(member.getName().equals("xxx")){

                x1++;

                logger.warning("########################################################################################: " + x1);

                if(x1 == 17){
                    logger.warning("Chegou a 17 xxx");
                    return;
                }

                throw new JMSException("xxx");

            }

            if(member.getName().equals("yyy")){

                x2++;

                logger.warning("########################################################################################: " + x2);

                if(x2 == 17){
                    logger.warning("Chegou a 17 yyy");
                    return;
                }

                throw new RuntimeException("yyy");

            }

            logger.info(() -> "Received: " + member);

        } catch (JMSException ex) {
            throw new RuntimeException("MemberQueueMDB JMSException :(");
        }

    }

}