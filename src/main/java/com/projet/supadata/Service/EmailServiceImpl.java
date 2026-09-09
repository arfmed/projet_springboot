package com.projet.supadata.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class EmailServiceImpl implements EmailService{
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void SendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        try {
            emailSender.send(message);
            System.out.println("Email envoyé avec succès à : " + to);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MimeMessage createMimeMessage() {
        return emailSender.createMimeMessage();
    }

    @Override
    public void SendEmail(MimeMessage message) {
        emailSender.send(message);
    }

    @Override
    public void sendCardEmail(
            String particulierEmail,
            String employeEmail,
            String employeName,
            Long employeId
    ) {

        String cardLink = "http://localhost:4200/card" ;

        /*
         * MAIL 1 → au particulier
         */

        SimpleMailMessage mailToParticulier = new SimpleMailMessage();
        mailToParticulier.setTo(particulierEmail);
        mailToParticulier.setSubject("Votre carte employeur");

        mailToParticulier.setText(
                "Bonjour,\n\n" +
                        "Vous avez consulté la carte employeur de : "
                        + employeName + "\n\n" +

                        "Votre carte a bien été enregistrée.\n\n" +

                        "Voici le lien de la carte :\n"
                        + cardLink + "\n\n" +

                        "Merci."
        );

        emailSender.send(mailToParticulier);


        /*
         * MAIL 2 → à l'employer
         */

        SimpleMailMessage mailToEmploye = new SimpleMailMessage();
        mailToEmploye.setTo(employeEmail);
        mailToEmploye.setSubject("Consultation de votre carte");

        mailToEmploye.setText(
                "Bonjour,\n\n" +
                        "Le particulier : "
                        + particulierEmail +
                        "\n\n a consulté votre carte employeur.\n\n" +

                        "Merci."
        );

        emailSender.send(mailToEmploye);
    }
}
