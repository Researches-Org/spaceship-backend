package com.xl.spaceship.application.command;

public final class ReceiveSalvoCmd {

    private String[] salvo;

    public ReceiveSalvoCmd() { }

    public ReceiveSalvoCmd(String[] salvo) {
        this.salvo = salvo;
    }

    public String[] getSalvo() {
        return salvo;
    }
}
