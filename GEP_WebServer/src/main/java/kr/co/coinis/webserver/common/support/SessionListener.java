package kr.co.coinis.webserver.common.support;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	System.out.println("sessionCreated - add one session into counter");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	System.out.println("sessionDestroyed - deduct one session from counter");
  }
}