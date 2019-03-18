package de.homedev.jms.receiver.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@ConditionalOnClass(ActiveMQConnectionFactory.class)
@ConditionalOnMissingBean(ConnectionFactory.class)
@EnableConfigurationProperties(ActiveMQProperties.class)
@Configuration
public class ActiveMQConfig {
	@Autowired
	private ActiveMQProperties activeMqProperties;

	@Value("${app.jms.maxConnections:0}")
	private int maxConnections;

	@Value("${app.jms.sendTimeout:0}")
	private int sendTimeout;

	@Bean
	@Primary
	public ConnectionFactory jmsConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(activeMqProperties.getUser(), activeMqProperties.getPassword(),
				activeMqProperties.getBrokerUrl());
		activeMQConnectionFactory.setSendTimeout(sendTimeout);
		org.apache.activemq.jms.pool.PooledConnectionFactory pool = new PooledConnectionFactory();
		if (0 != maxConnections) {
			pool.setMaxConnections(maxConnections);
		}
		pool.setConnectionFactory(activeMQConnectionFactory);
		return pool;
	}
}
