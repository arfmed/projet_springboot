package com.projet.supadata.Service;

import jakarta.mail.internet.MimeMessage;

public interface EmailParService {
    public MimeMessage createMimeMessage();
    public void SendEmail(MimeMessage message);
}
