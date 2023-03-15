package com.example.tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;


public class HelloController {

    public HelloController() {
    }

    ObservableList<String> gameModeChoiceBoxItems =
            FXCollections.observableArrayList("Gra lokalna", "Gra vs Komputer");

    Game game;
    Image imageWin = new Image(getClass().getResourceAsStream("/img/winner.png"));
    Image imageLose = new Image(getClass().getResourceAsStream("/img/loser.png"));
    Image imageTie = new Image(getClass().getResourceAsStream("/img/tie.png"));

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
    @FXML
    private Ellipse winEllipse;
    @FXML
    private ImageView imageView;

    public void initialize() {
        cbGameMods.setItems(gameModeChoiceBoxItems);
        lPlayer1.setVisible(false);
        lPlayer2.setVisible(false);
        tfPlayer1.setVisible(false);
        tfPlayer2.setVisible(false);
        imageView.setVisible(false);
        bStart.setVisible(false);
        winEllipse.setVisible(false);
    }

    //selecting a game mode
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
        bConfirm.setDisable(true);
        cbGameMods.setDisable(true);
    }




    //determining player names and creating the game
    @FXML
    protected void bStartClick() {
        String gameMode = cbGameMods.getValue().toString();
        //local game
        if (gameMode.equals("Gra lokalna")) {
            //two players with usernames
            if (!tfPlayer1.getText().equals("") && !tfPlayer2.getText().equals("")){
                //the same two players
                if (tfPlayer1.getText().equals(tfPlayer2.getText())){
                    tfPlayer1.setText(tfPlayer2.getText() + "1");
                    tfPlayer2.setText(tfPlayer2.getText() + "2");
                }
                game = new Game(gameMode, tfPlayer1.getText(), tfPlayer2.getText());
            }
            //first player with username
            else if (!tfPlayer1.getText().equals("") && tfPlayer2.getText().equals("")){
                game = new Game(gameMode, tfPlayer1.getText(), "Player2");
            }
            //second player with username
            else if (tfPlayer1.getText().equals("") && !tfPlayer2.getText().equals("")){
                game = new Game(gameMode, "Player1", tfPlayer2.getText());
            }
            //two players without usernames
            else {
                game = new Game(gameMode, "Player1", "Player2");
            }
        }
        else {
            //game against computer
            lPlayer1.setVisible(true);
            tfPlayer1.setVisible(true);
            if (!tfPlayer1.getText().equals("")){
                game = new Game(gameMode, tfPlayer1.getText());
            }
            else {
                game = new Game(gameMode, "Player1");
            }

        }
        cbGameMods.setVisible(false);
        preparePane();
        if(game.getWhoseTurn() == 1) lChooseGameMod.setText("Kolej gracza: " + game.getPlayer1() + " - x");
        else {
            lChooseGameMod.setText("Kolej gracza: " + game.getPlayer2() + " - x");
            computerGame();
        }
        lChooseGameMod.setLayoutX(375);
    }

    //prepare pane to game
    protected void preparePane(){
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

    protected void blockBoard(){
        bGame00.setDisable(true);
        bGame01.setDisable(true);
        bGame02.setDisable(true);
        bGame10.setDisable(true);
        bGame11.setDisable(true);
        bGame12.setDisable(true);
        bGame20.setDisable(true);
        bGame21.setDisable(true);
        bGame22.setDisable(true);
    }

    protected void prepareWinEllipseParam(double x, double y, double radx, double rady){
        winEllipse.setLayoutX(x);
        winEllipse.setLayoutY(y);
        winEllipse.setRadiusX(radx);
        winEllipse.setRadiusY(rady);
    }

    protected void prepareWinEllipse(int[] result){
        winEllipse.setVisible(true);
        if(result[1] == 0 && result[2] == 0 && result[5] == 0 && result[6] == 2) {//row1
            prepareWinEllipseParam(185, 105, 150, 35);
        } else if(result[1] == 1 && result[2] == 0 && result[5] == 1 && result[6] == 2){//row2
            prepareWinEllipseParam(185, 207, 150, 35);
        }
        else if(result[1] == 2 && result[2] == 0 && result[5] == 2 && result[6] == 2){//row3
            prepareWinEllipseParam(185, 309, 150, 35);
        }
        else if(result[1] == 0 && result[2] == 0 && result[5] == 2 && result[6] == 0){//column1
            prepareWinEllipseParam(85, 205, 35, 150);
        }
        else if(result[1] == 0 && result[2] == 1 && result[5] == 2 && result[6] == 1){//column2
            prepareWinEllipseParam(185, 205, 35, 150);
        }
        else if(result[1] == 0 && result[2] == 2 && result[5] == 2 && result[6] == 2){//column3
            prepareWinEllipseParam(285, 205, 35, 150);
        }
        else if(result[1] == 0 && result[2] == 0 && result[5] == 2 && result[6] == 2){//diagonal1
            prepareWinEllipseParam(182, 205, 38, 190);
            winEllipse.setRotate(-45);
        }
        else if(result[1] == 0 && result[2] == 2 && result[5] == 2 && result[6] == 0){//diagonal2
            prepareWinEllipseParam(187, 205, 38, 190);
            winEllipse.setRotate(45);
        }

    }

    protected void setEndImage(char sign){
        imageView.setVisible(true);
        imageView.setLayoutX(370);
        if(game.getGameMode().equals("Gra lokalna")) imageView.setImage(imageWin);
        else {
            if(sign == 'w') imageView.setImage(imageWin);
            if(sign == 'l'){
                imageView.setLayoutX(400);
                imageView.setImage(imageLose);
            }
        }
        imageView.setVisible(true);
        imageView.setX(30);
        imageView.setY(-50);
        if(sign == 't') {
            imageView.setImage(imageTie);
            imageView.setX(0);
        }
    }

    protected void checkCurrentGameStatus(){
        if(game.getCounter() != 0){
            int[] resultX = {0,0,0,0,0,0,0};
            int[] resultY = {0,0,0,0,0,0,0};
            game.win('x', resultX);
            game.win('o', resultY);
            if(resultX[0]==1){
                lChooseGameMod.setLayoutX(410);
                if(game.getWhoStart() == 1){
                    lChooseGameMod.setText("Wygrał " + game.getPlayer1());
                    setEndImage('w');
                }
                else{
                    lChooseGameMod.setText("Wygrał " + game.getPlayer2());
                    setEndImage('l');
                }
                prepareWinEllipse(resultX);
                blockBoard();
            }
            else if(resultY[0]==1){
                lChooseGameMod.setLayoutX(410);
                if(game.getWhoStart() == 2){

                    lChooseGameMod.setText("Wygrał " + game.getPlayer1());
                    setEndImage('w');
                }
                else{
                    lChooseGameMod.setText("Wygrał " + game.getPlayer2());
                    setEndImage('l');
                }
                prepareWinEllipse(resultY);
                blockBoard();
            }
            for (int x:resultX) {
                System.out.print(x + " ");
            }
            System.out.println(" X ");
            for (int y:resultY) {
                System.out.print(y + " ");
            }
            System.out.print(" Y ");
            System.out.println();
            System.out.println();
        } else {
            lChooseGameMod.setText("Remis!");
            lChooseGameMod.setLayoutX(450);
            setEndImage('t');
        }
    }

    protected void computerGame(){
        if(game.getGameMode().equals("Gra vs Komputer")){
            int[] result = new int[2];
            game.ComputerMove(result);
            System.out.println(result);
            if(result[0] == 0 && result[1] == 0) {
                bGame00.setText(game.move(bGame00.getId(), lChooseGameMod));
                bGame00.setDisable(true);
            }
            if(result[0] == 0 && result[1] == 1) {
                bGame01.setText(game.move(bGame01.getId(), lChooseGameMod));
                bGame01.setDisable(true);
            }
            if(result[0] == 0 && result[1] == 2) {
                bGame02.setDisable(true);
                bGame02.setText(game.move(bGame02.getId(), lChooseGameMod));
            }
            if(result[0] == 1 && result[1] == 0) {
                bGame10.setDisable(true);
                bGame10.setText(game.move(bGame10.getId(), lChooseGameMod));
            }
            if(result[0] == 1 && result[1] == 1) {
                bGame11.setDisable(true);
                bGame11.setText(game.move(bGame11.getId(), lChooseGameMod));
            }
            if(result[0] == 1 && result[1] == 2) {
                bGame12.setDisable(true);
                bGame12.setText(game.move(bGame12.getId(), lChooseGameMod));
            }
            if(result[0] == 2 && result[1] == 0) {
                bGame20.setDisable(true);
                bGame20.setText(game.move(bGame20.getId(), lChooseGameMod));
            }
            if(result[0] == 2 && result[1] == 1) {
                bGame21.setDisable(true);
                bGame21.setText(game.move(bGame21.getId(), lChooseGameMod));
            }
            if(result[0] == 2 && result[1] == 2) {
                bGame22.setDisable(true);
                bGame22.setText(game.move(bGame22.getId(), lChooseGameMod));
            }
        }
    }

    @FXML
    protected void bGameClick00() {
        bGame00.setText(game.move(bGame00.getId(), lChooseGameMod));
        bGame00.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }



    @FXML
    protected void bGameClick01() {
        bGame01.setText(game.move(bGame01.getId(), lChooseGameMod));
        bGame01.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }

    }

    @FXML
    protected void bGameClick02() {
        bGame02.setText(game.move(bGame02.getId(), lChooseGameMod));
        bGame02.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick10() {
        bGame10.setText(game.move(bGame10.getId(), lChooseGameMod));
        bGame10.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick11() {
        bGame11.setText(game.move(bGame11.getId(), lChooseGameMod));
        bGame11.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick12() {
        bGame12.setText(game.move(bGame12.getId(), lChooseGameMod));
        bGame12.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick20() {
        bGame20.setText(game.move(bGame20.getId(), lChooseGameMod));
        bGame20.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick21() {
        bGame21.setText(game.move(bGame21.getId(), lChooseGameMod));
        bGame21.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }

    @FXML
    protected void bGameClick22() {
        bGame22.setText(game.move(bGame22.getId(), lChooseGameMod));
        bGame22.setDisable(true);
        checkCurrentGameStatus();
        if(lChooseGameMod.getText().charAt(1) != 'W' && game.getGameMode().equals("Gra vs Komputer") && game.getCounter() != 0) {
            computerGame();
            checkCurrentGameStatus();
        }
    }



}