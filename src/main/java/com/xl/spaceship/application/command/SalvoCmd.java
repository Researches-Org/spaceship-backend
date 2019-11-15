package com.xl.spaceship.application.command;

public final class SalvoCmd {

    private String[] salvo;

    public SalvoCmd() { }

    public SalvoCmd(String[] salvo) {
        this.salvo = salvo;
    }

    public String[] getSalvo() {
        return salvo;
    }
}
