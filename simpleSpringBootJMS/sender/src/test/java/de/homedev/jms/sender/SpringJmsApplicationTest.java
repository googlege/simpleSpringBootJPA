
package de.homedev.jms.sender;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.apache.activemq.broker.BrokerService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import de.homedev.jms.sender.config.ReceiverConfig;

@RunWith(SpringRunner.class)
@EnableJms
@SpringBootTest
@TestPropertySource(value = "classpath:application-test.properties")
@Import(value = { ReceiverConfig.class })
public class SpringJmsApplicationTest {

    @Autowired
    private SenderV1 senderV1;

    @Autowired
    private SenderV2 senderV2;

    @Autowired
    private Receiver receiver;

    @Autowired
    private static BrokerService broker = null;

    @BeforeClass
    public static void setUp() throws Exception {
        if (broker != null) {
            broker.autoStart();
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (broker != null) {
            broker.stop();
        }
    }

    @Test
    public void testReceiveV1() throws Exception {
        senderV1.send("Hello Spring JMS ActiveMQ!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }

    @Test
    public void testReceiveV2() throws Exception {
        senderV2.send("Hello Spring JMS ActiveMQ!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }
}