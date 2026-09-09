package com.projet.supadata.Service;

import jakarta.mail.internet.MimeMessage;

public interface EmailService {
    public void SendSimpleMessage(String to,String subject,String text);

    public MimeMessage createMimeMessage();

    public void SendEmail(MimeMessage message);
    public void sendCardEmail(String particulierEmail,
                              String employeEmail,
                              String employeName,
                              Long employeId);
}
