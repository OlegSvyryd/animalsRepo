package com.animals.app.controller.resource;

import java.io.Serializable;
/**
 * Created by aquaneo on 8/13/2015.
 */
public class Feedback implements Serializable {
    private Mail mail;
    private String gRecaptchaResponse;

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getgRecaptchaResponse() {
        return gRecaptchaResponse;
    }

    public void setgRecaptchaResponse(String gRecaptchaResponse) {
        this.gRecaptchaResponse = gRecaptchaResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback that = (Feedback) o;

        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        return !(gRecaptchaResponse != null ? !gRecaptchaResponse.equals(that.gRecaptchaResponse) : that.gRecaptchaResponse != null);

    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (gRecaptchaResponse != null ? gRecaptchaResponse.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "Mail=" + mail +
                ", grecaptchaesponse='" + gRecaptchaResponse +
                '}';
    }
}

class Mail {
    private String signup;
    private String email;
    private String text;

    public String getSignup() {
        return signup;
    }

    public void setSignup(String signup) {
        this.signup = signup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail that = (Mail) o;

        if (signup != null ? !signup.equals(that.signup) : that.signup != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return !(text != null ? !text.equals(that.text) : that.text != null);

    }

    @Override
    public int hashCode() {
        int result = signup != null ? signup.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "email=" + email +
                ", text='" + text +
                ", signup='" + signup +
                '}';
    }
}