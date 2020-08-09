package org.example.registration_with_check.service;

import java.util.concurrent.TimeoutException;

/**
 * Ориентировочный интерфейс нашего messaging решения.
 */
public interface MessagingService {
    /**
     * Отправка сообщения в шину.
     *
     * @param msg сообщение для отправки.
     *
     * @return идентификатор отправленного сообщения (correlationId)
     */
    String send(String msg);

    /**
     * Встает на ожидание ответа по сообщению с messageId.
     *
     * Редко, но может кинуть исключение по таймауту.
     *
     * @param messageId идентификатор сообщения, на которое ждем ответ.
     * @return Тело ответа.
     */
    Boolean receive(String messageId) throws TimeoutException;
}
