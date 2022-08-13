package com.lantin.web.config.kafka;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;


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
	public KafkaListenerContainerFactory<?> activityKafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	                                                                              @Autowired @Qualifier("activityKafkaConsumerFactory") ConsumerFactory consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		configurer.configure(factory, consumerFactory);
		return factory;
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
	public KafkaListenerContainerFactory<?> gameLoginKafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
	                                                                               @Autowired @Qualifier("gameLoginKafkaConsumerFactory") ConsumerFactory consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		configurer.configure(factory, consumerFactory);
		return factory;
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

}
