package com.example.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.example.tictactoe.MiniMaxAI.getBestMove;

public class HelloController {

    public HelloController() {
    }

    ObservableList<String> gameModeChoiceBoxItems =
            FXCollections.observableArrayList("Gra lokalna", "Gra vs Komputer");

    int who = 1;
    public static int counter = 9;
    String gameMode = "";
    String player1 = "";
    String player2 = "";

    char board[][] = {{'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}};

    @FXML
    private Button bGame00;
    @FXML
    private Button bGame01;
    @FXML
    private Button bGame02;
    @FXML
    private Button bGame10;
    @FXML
    private Button bGame11;
    @FXML
    private Button bGame12;
    @FXML
    private Button bGame20;
    @FXML
    private Button bGame21;
    @FXML
    private Button bGame22;
    @FXML
    private Button bConfirm;
    @FXML
    private Button bStart;
    @FXML
    private Button bReset;
    @FXML
    private Label lChooseGameMod;
    @FXML
    private TextField tfPlayer1;
    @FXML
    private TextField tfPlayer2;
    @FXML
    private Label lPlayer1;
    @FXML
    private Label lPlayer2;
    @FXML
    private ChoiceBox cbGameMods;

    public void initialize() {
        cbGameMods.setItems(gameModeChoiceBoxItems);
        lPlayer1.setVisible(false);
        lPlayer2.setVisible(false);
        tfPlayer1.setVisible(false);
        tfPlayer2.setVisible(false);
        bStart.setVisible(false);
        bReset.setVisible(false);
    }

    protected boolean game(String sign) {
        return bGame00.getText().equals(sign) && bGame01.getText().equals(sign) && bGame02.getText().equals(sign) ||
                bGame10.getText().equals(sign) && bGame11.getText().equals(sign) && bGame12.getText().equals(sign) ||
                bGame20.getText().equals(sign) && bGame21.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame00.getText().equals(sign) && bGame10.getText().equals(sign) && bGame20.getText().equals(sign) ||
                bGame01.getText().equals(sign) && bGame11.getText().equals(sign) && bGame21.getText().equals(sign) ||
                bGame02.getText().equals(sign) && bGame12.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame00.getText().equals(sign) && bGame11.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame02.getText().equals(sign) && bGame11.getText().equals(sign) && bGame20.getText().equals(sign);
    }

    protected void endGame() {
        bGame00.setDisable(true);
        bGame01.setDisable(true);
        bGame02.setDisable(true);
        bGame10.setDisable(true);
        bGame11.setDisable(true);
        bGame12.setDisable(true);
        bGame20.setDisable(true);
        bGame21.setDisable(true);
        bGame22.setDisable(true);
        bReset.setVisible(true);
    }


    @FXML
    protected void bGameClick(Button button) {
        if (gameMode.equals("Gra lokalna")) localGame(button);
        if (gameMode.equals("Gra vs Komputer")) computerGame(button);

    }


    @FXML
    protected void localGame(Button button) {
        if (who == 1) {
            counter--;
            lChooseGameMod.setText("Kolej gracza: " + player2);
            button.setText("x");
            if (game("x")) {
                endGame();
                lChooseGameMod.setText("Wygrał " + player1);
            } else if (counter == 0) {
                bReset.setVisible(true);
                lChooseGameMod.setText("Remis!!!");
            }
            who = 2;
        } else {
            counter--;
            lChooseGameMod.setText("Kolej gracza: " + player1);
            button.setText("o");
            if (game("o")) {
                endGame();
                lChooseGameMod.setText("Wygrał " + player2);
            } else if (counter == 0) {
                bReset.setVisible(true);
                lChooseGameMod.setText("Remis!!!");
            }
            who = 1;
        }
        System.out.println(counter);
        button.setDisable(true);
    }


    @FXML
    protected void computerGame(Button button) {

        counter--;
        if (who == 1) {
            lChooseGameMod.setText("Kolej gracza: Komputer");
            button.setText("x");
            if (button == bGame00) board[0][0] = 'x';
            if (button == bGame01) board[0][1] = 'x';
            if (button == bGame02) board[0][2] = 'x';
            if (button == bGame10) board[1][0] = 'x';
            if (button == bGame11) board[1][1] = 'x';
            if (button == bGame12) board[1][2] = 'x';
            if (button == bGame20) board[2][0] = 'x';
            if (button == bGame21) board[2][1] = 'x';
            if (button == bGame22) board[2][2] = 'x';
            if (game("x")) {
                endGame();
                lChooseGameMod.setText("Wygrał " + player1);
            } else if (counter == 0) {
                bReset.setVisible(true);
                lChooseGameMod.setText("Remis!!!");
            }
            who = 2;
            if (counter > 1) {
                int[] bestMove = getBestMove(board);
                System.out.println(bestMove[0] + " " + bestMove[1]);
                if(bestMove[0]>=0 && bestMove[1]>=0){
                    board[bestMove[0]][bestMove[1]] = 'o';
                } else {
                    lChooseGameMod.setText("Remis!!!");//sprawdzanie wygranej trzeba zrobic
                }

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) System.out.print(board[i][j] + " ");
                    System.out.println();
                }
                if (bestMove[0] == 0 && bestMove[1] == 0) bGameClick00();
                if (bestMove[0] == 0 && bestMove[1] == 1) bGameClick01();
                if (bestMove[0] == 0 && bestMove[1] == 2) bGameClick02();
                if (bestMove[0] == 1 && bestMove[1] == 0) bGameClick10();
                if (bestMove[0] == 1 && bestMove[1] == 1) bGameClick11();
                if (bestMove[0] == 1 && bestMove[1] == 2) bGameClick12();
                if (bestMove[0] == 2 && bestMove[1] == 0) bGameClick20();
                if (bestMove[0] == 2 && bestMove[1] == 1) bGameClick21();
                if (bestMove[0] == 2 && bestMove[1] == 2) bGameClick22();
            }
        } else {
            lChooseGameMod.setText("Kolej gracza: " + player1);

            if (!game("x")) {
                button.setText("o");
                if (game("o")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                } else if (counter == 0) {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            } else lChooseGameMod.setText("Wygrał " + player1);
        }
        System.out.println(counter);
        button.setDisable(true);
    }

    @FXML
    protected void bGameClick00() {
        bGameClick(bGame00);
    }

    @FXML
    protected void bGameClick01() {
        bGameClick(bGame01);
    }

    @FXML
    protected void bGameClick02() {
        bGameClick(bGame02);
    }

    @FXML
    protected void bGameClick10() {
        bGameClick(bGame10);
    }

    @FXML
    protected void bGameClick11() {
        bGameClick(bGame11);
    }

    @FXML
    protected void bGameClick12() {
        bGameClick(bGame12);
    }

    @FXML
    protected void bGameClick20() {
        bGameClick(bGame20);
    }

    @FXML
    protected void bGameClick21() {
        bGameClick(bGame21);
    }

    @FXML
    protected void bGameClick22() {
        bGameClick(bGame22);
    }

    @FXML
    protected void bConfirmClick() {
        System.out.println(cbGameMods.getValue());
        String gameMode = (String) cbGameMods.getValue();
        cbGameMods.setDisable(true);
        if (gameMode.equals("Gra lokalna")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            lPlayer2.setVisible(true);
            tfPlayer2.setVisible(true);
        }
        if (gameMode.equals("Gra vs Komputer")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);

        }
        bStart.setVisible(true);
        //bGame00.setDisable(false);
    }

    @FXML
    protected void bStartClick() {
        player1 = "Gracz1";
        player2 = "Gracz2";
        System.out.println(cbGameMods.getValue());
        gameMode = (String) cbGameMods.getValue();
        cbGameMods.setDisable(true);
        if (gameMode.equals("Gra lokalna")) {
            if (!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();
            if (!tfPlayer2.getText().equals("")) player2 = tfPlayer2.getText();
            if (player1.equals(player2)) {
                player1 += "1";
                player2 += "2";
            }
            System.out.println(player1);
            System.out.println(player2);
        }
        if (gameMode.equals("Gra online")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            if (!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();
        }
        if (gameMode.equals("Gra vs Komputer")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            if (!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();

        }
        lChooseGameMod.setText("Kolej gracza: " + player1);
        cbGameMods.setVisible(false);
        bConfirm.setVisible(false);
        lPlayer1.setVisible(false);
        lPlayer2.setVisible(false);
        tfPlayer1.setVisible(false);
        tfPlayer2.setVisible(false);
        bStart.setVisible(false);
        bGame00.setDisable(false);
        bGame01.setDisable(false);
        bGame02.setDisable(false);
        bGame10.setDisable(false);
        bGame11.setDisable(false);
        bGame12.setDisable(false);
        bGame20.setDisable(false);
        bGame21.setDisable(false);
        bGame22.setDisable(false);
    }

    @FXML
    protected void bResetClick() {
        bReset.setVisible(false);
        lChooseGameMod.setText("Wybierz tryb gry!");
        cbGameMods.setVisible(true);
        cbGameMods.setDisable(false);
        bConfirm.setVisible(true);
        counter = 9;
        bGame00.setText("");
        bGame01.setText("");
        bGame02.setText("");
        bGame10.setText("");
        bGame11.setText("");
        bGame12.setText("");
        bGame20.setText("");
        bGame21.setText("");
        bGame22.setText("");
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '_';

        who = 1;
    }

}