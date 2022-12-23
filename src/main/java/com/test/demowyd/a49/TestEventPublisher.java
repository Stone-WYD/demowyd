package com.test.demowyd.a49;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestEventPublisher {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("applicationEventMulticaster",MyApplicationEventMulticaster.class);
        context.refresh();

        context.publishEvent(new Object());
        context.publishEvent("aaa");
        context.publishEvent(new Bean1());
    }

    interface  Inter{

    }

    static class Bean1 implements Inter{

    }

    static class MyApplicationEventMulticaster implements ApplicationEventMulticaster{

        private List<ApplicationListener> listeners = new ArrayList<>();

        {
            listeners.add(new GenericApplicationListener() {
                @Override
                public boolean supportsEventType(ResolvableType eventType) {
                    System.out.println(eventType);
                    return Inter.class.isAssignableFrom(eventType.getGeneric(0).toClass());
                }

                @Override
                public void onApplicationEvent(ApplicationEvent event) {
                    if (event instanceof PayloadApplicationEvent payloadApplicationEvent){
                        System.out.println(payloadApplicationEvent.getPayload());
                    }
                }
            });
        }

        @Override
        public void addApplicationListener(ApplicationListener<?> listener) {

        }

        @Override
        public void addApplicationListenerBean(String listenerBeanName) {

        }

        @Override
        public void removeApplicationListener(ApplicationListener<?> listener) {

        }

        @Override
        public void removeApplicationListenerBean(String listenerBeanName) {

        }

        @Override
        public void removeApplicationListeners(Predicate<ApplicationListener<?>> predicate) {

        }

        @Override
        public void removeApplicationListenerBeans(Predicate<String> predicate) {

        }

        @Override
        public void removeAllListeners() {

        }

        @Override
        public void multicastEvent(ApplicationEvent event) {

        }

        @Override
        public void multicastEvent(ApplicationEvent event, ResolvableType eventType) {
            listeners.stream().filter(applicationListener -> {
                if (eventType == null){
                    return false;
                }
                if (applicationListener instanceof GenericApplicationListener genericApplicationListener){
                    return genericApplicationListener.supportsEventType(eventType);
                }
                return false;
            }).forEach(applicationListener -> {
                applicationListener.onApplicationEvent(event);
            });
        }
    }
}
