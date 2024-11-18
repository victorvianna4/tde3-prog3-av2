package src;

import controller.FuncionarioController;
import view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        FuncionarioController controller = new FuncionarioController();
        FuncionarioView view = new FuncionarioView(controller);
        view.iniciar();
    }
}
