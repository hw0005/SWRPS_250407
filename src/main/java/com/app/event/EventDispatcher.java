package com.app.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventDispatcher {
	private final RabbitTemplate rabbitTemplate;

	// Rps 관련 정보를 전달하기 위한 익스체인지
	private final String rpsExchange;

	// 특정 이벤트를 전송하기 위한 라우팅 키
	private final String rpsSolvedRoutingKey;

	EventDispatcher(final RabbitTemplate rabbitTemplate,
			@Value("${spring.rabbitmq.template.exchange}") final String rpsExchange,
			@Value("${spring.rabbitmq.template.routing-key}") final String rpsSolvedRoutingKey) {
		this.rabbitTemplate = rabbitTemplate;
		this.rpsExchange = rpsExchange;
		this.rpsSolvedRoutingKey = rpsSolvedRoutingKey;
	}

	public void send(final RpsSolvedEvent rpsSolvedEvent) {
		rabbitTemplate.convertAndSend(rpsExchange, rpsSolvedRoutingKey, rpsSolvedEvent);
	}
}
