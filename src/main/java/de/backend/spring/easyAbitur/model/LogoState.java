package de.backend.spring.easyAbitur.model;

public enum LogoState {
    SWL("LWS, LAS, NETZE");

    private String text;

    LogoState(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static LogoState getLogoState(String text) {
        for (LogoState b : LogoState.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
