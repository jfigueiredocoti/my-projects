package br.com.petz.atendimento.petscenterapi.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("h2")
public class H2 {

    private org.h2.tools.Server webServer;
	
	
	@Value("${webserver.h2-console-port}")
	Integer h2ConsolePort;

	@EventListener(ContextRefreshedEvent.class)
	public void start() throws java.sql.SQLException {
	
	System.out.println("#### DATABASE - Starting h2 console at port "+ h2ConsolePort);
	this.webServer = org.h2.tools.Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start();
	}

	@EventListener(ContextClosedEvent.class)
	public void stop() {
	this.webServer.stop();
	}	

}
