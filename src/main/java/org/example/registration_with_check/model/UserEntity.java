package org.example.registration_with_check.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String login;
    private String pass;
    private String mail;
    private String fio;
    private String messageId;
    private Boolean valid;
    private Boolean sendMail;

    public UserEntity(){

    }

    public UserEntity(String login, String pass, String mail, String fio) {
        this.login = login;
        this.pass = pass;
        this.mail = mail;
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getSendMail() {
        return sendMail;
    }

    public void setSendMail(Boolean sendMail) {
        this.sendMail = sendMail;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", mail='" + mail + '\'' +
                ", FIO='" + fio + '\'' +
                ", messageId='" + messageId + '\'' +
                ", valid=" + valid +
                ", sendMail=" + sendMail;
    }
}
