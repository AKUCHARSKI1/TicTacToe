package com.example.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
  //  @FXML
  //  private Button b1;

    public HelloController() {
    }

    ObservableList<String> gameModeChoiceBoxItems =
            FXCollections.observableArrayList("Gra lokalna", "Gra online", "Gra vs Komputer");

    int who = 1;
    int counter = 9;
    String gameMode = "";
    String player1 = "";
    String player2 = "";
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
    public void initialize()
    {
        cbGameMods.setItems(gameModeChoiceBoxItems);
        lPlayer1.setVisible(false);
        lPlayer2.setVisible(false);
        tfPlayer1.setVisible(false);
        tfPlayer2.setVisible(false);
        bStart.setVisible(false);
        bReset.setVisible(false);
    }
    protected boolean game(String sign)
    {
        if(bGame00.getText().equals(sign) && bGame01.getText().equals(sign) && bGame02.getText().equals(sign) ||
                bGame10.getText().equals(sign) && bGame11.getText().equals(sign) && bGame12.getText().equals(sign) ||
                bGame20.getText().equals(sign) && bGame21.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame00.getText().equals(sign) && bGame10.getText().equals(sign) && bGame20.getText().equals(sign) ||
                bGame01.getText().equals(sign) && bGame11.getText().equals(sign) && bGame21.getText().equals(sign) ||
                bGame02.getText().equals(sign) && bGame12.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame00.getText().equals(sign) && bGame11.getText().equals(sign) && bGame22.getText().equals(sign) ||
                bGame02.getText().equals(sign) && bGame11.getText().equals(sign) && bGame20.getText().equals(sign)) return true;
        else return false;
    }
    protected void endGame()
    {
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
    protected void bGameClick00() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame00.setText("0");
                if(game("0"))
                {
                    endGame();

                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame00.setText("X");
                if(game("X"))
                {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame00.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick01() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame01.setText("0");
                if(game("0"))
                {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame01.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame01.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick02() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame02.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame02.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame02.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick10() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame10.setText("0");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame10.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame10.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick11() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame11.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame11.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame11.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick12() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame12.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame12.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }

            System.out.println(counter);
            bGame12.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick20() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame20.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame20.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame20.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick21() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame21.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame21.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame21.setDisable(true);
        }
    }
    @FXML
    protected void bGameClick22() {
        if(gameMode.equals("Gra lokalna")) {
            if(who == 1)
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player2);
                bGame22.setText("0");
                if(game("0")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player1);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 2;
            }
            else
            {
                counter--;
                lChooseGameMod.setText("Kolej gracza: " + player1);
                bGame22.setText("X");
                if(game("X")) {
                    endGame();
                    lChooseGameMod.setText("Wygrał " + player2);
                }
                else if(counter == 0)
                {
                    bReset.setVisible(true);
                    lChooseGameMod.setText("Remis!!!");
                }
                who = 1;
            }
            System.out.println(counter);
            bGame22.setDisable(true);
        }
    }

    @FXML
    protected void bConfirmClick() {
        System.out.println(cbGameMods.getValue());
        String gameMode = (String) cbGameMods.getValue();
        cbGameMods.setDisable(true);
        if(gameMode.equals("Gra lokalna")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            lPlayer2.setVisible(true);
            tfPlayer2.setVisible(true);
        }
        if(gameMode.equals("Gra online")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
        }
        if(gameMode.equals("Gra vs Komputer")) {
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
        if(gameMode.equals("Gra lokalna")) {
            if(!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();
            if(!tfPlayer2.getText().equals("")) player2 = tfPlayer2.getText();
            if(player1.equals(player2)) {
                player1+="1";
                player2+="2";
            }
            System.out.println(player1);
            System.out.println(player2);
        }
        if(gameMode.equals("Gra online")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            if(!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();
        }
        if(gameMode.equals("Gra vs Komputer")) {
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            if(!tfPlayer1.getText().equals("")) player1 = tfPlayer1.getText();

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
    protected void bResetClick()
    {
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
    }

}