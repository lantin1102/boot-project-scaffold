package com.lantin.web.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Setter
@Getter
public class TestEvent extends ApplicationEvent {

	private final String content;

	private Integer id;

	public TestEvent(Object source, String content) {
		super(source);
		this.content = content;
	}
}
