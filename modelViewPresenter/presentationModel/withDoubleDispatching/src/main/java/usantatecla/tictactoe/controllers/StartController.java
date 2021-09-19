package usantatecla.tictactoe.controllers;

import usantatecla.tictactoe.models.Session;

public class StartController extends Controller {

    public StartController(Session session) {
        super(session);
    }

    @Override
    public void accept(ControllersVisitor controllersVisitor) {
        controllersVisitor.visit(this);
    }

}
