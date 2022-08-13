package com.lantin.web.config.kafka;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.time.Duration;


@Configuration
public class KafkaConfiguration {


	// kafka 默认producer和consumer的serializer都是StringSerializer
	// @Bean
	// Serializer<String> stringSerializer() {
	// 	return new StringSerializer();
	// }

	/*
	 * 活动kafka start
	 */

	@Bean
	public ProducerFactory<?, ?> kafkaProducerFactory(
			ObjectProvider<DefaultKafkaProducerFactoryCustomizer> customizers,
			KafkaProperties properties) {
		DefaultKafkaProducerFactory<?, ?> factory = new DefaultKafkaProducerFactory<>(
				properties.buildProducerProperties());
		String transactionIdPrefix = properties.getProducer().getTransactionIdPrefix();
		if (transactionIdPrefix != null) {
			factory.setTransactionIdPrefix(transactionIdPrefix);
		}
		customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
		return factory;
	}

	@Bean(name = {"kafkaListenerContainerFactory", "activityKafkaListenerContainerFactory"})
	public KafkaListenerContainerFactory<?> activityKafkaListenerContainerFactory(@Autowired @Qualifier("activityKafkaProperties") KafkaProperties properties,
	                                                                              @Autowired @Qualifier("activityKafkaConsumerFactory") ConsumerFactory consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		configure(containerFactory, consumerFactory, properties);
		return containerFactory;
	}

	@Bean
	public ConsumerFactory<?, ?> activityKafkaConsumerFactory(
			@Autowired @Qualifier("activityKafkaProperties") KafkaProperties properties) {
		return new DefaultKafkaConsumerFactory<>(
				properties.buildConsumerProperties());
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.kafka.activity")
	public KafkaProperties activityKafkaProperties() {
		return new KafkaProperties();
	}
	/*
	 * 活动kafka end
	 */


	/*
	 * 登录kafka start
	 */
	@Bean
	public KafkaListenerContainerFactory<?> gameLoginKafkaListenerContainerFactory(
			@Autowired @Qualifier("gameLoginKafkaProperties") KafkaProperties properties,
			@Autowired @Qualifier("gameLoginKafkaConsumerFactory") ConsumerFactory consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		configure(containerFactory, consumerFactory, properties);
		return containerFactory;
	}

	@Bean
	public ConsumerFactory<?, ?> gameLoginKafkaConsumerFactory(
			@Autowired @Qualifier("gameLoginKafkaProperties") KafkaProperties properties) {
		return new DefaultKafkaConsumerFactory<>(
				properties.buildConsumerProperties());
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.kafka.game-login")
	public KafkaProperties gameLoginKafkaProperties() {
		return new KafkaProperties();
	}
	/*
	 * 登录kafka end
	 */

	/**
	 * container配置 参考 org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer
	 */
	private void configure(ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory, ConsumerFactory consumerFactory, KafkaProperties properties) {
		containerFactory.setConsumerFactory(consumerFactory);
		configureListenerFactory(containerFactory, properties);
		configureContainer(containerFactory.getContainerProperties(), properties);
	}

	private void configureListenerFactory(ConcurrentKafkaListenerContainerFactory<Object, Object> factory, KafkaProperties properties) {
		PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
		KafkaProperties.Listener listener = properties.getListener();
		map.from(listener::getConcurrency).to(factory::setConcurrency);
		if (listener.getType().equals(KafkaProperties.Listener.Type.BATCH)) {
			factory.setBatchListener(true);
		}
	}

	private void configureContainer(ContainerProperties container, KafkaProperties properties) {
		PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();
		KafkaProperties.Listener listener = properties.getListener();
		map.from(listener::getAckMode).to(container::setAckMode);
		map.from(listener::getClientId).to(container::setClientId);
		map.from(listener::getAckCount).to(container::setAckCount);
		map.from(listener::getAckTime).as(Duration::toMillis).to(container::setAckTime);
		map.from(listener::getPollTimeout).as(Duration::toMillis).to(container::setPollTimeout);
		map.from(listener::getNoPollThreshold).to(container::setNoPollThreshold);
		map.from(listener.getIdleBetweenPolls()).as(Duration::toMillis).to(container::setIdleBetweenPolls);
		map.from(listener::getIdleEventInterval).as(Duration::toMillis).to(container::setIdleEventInterval);
		map.from(listener::getMonitorInterval).as(Duration::getSeconds).as(Number::intValue)
				.to(container::setMonitorInterval);
		map.from(listener::getLogContainerConfig).to(container::setLogContainerConfig);
		map.from(listener::isOnlyLogRecordMetadata).to(container::setOnlyLogRecordMetadata);
		map.from(listener::isMissingTopicsFatal).to(container::setMissingTopicsFatal);
	}
}
