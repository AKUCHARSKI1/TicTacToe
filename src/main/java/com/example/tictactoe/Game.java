package com.example.tictactoe;

import javafx.scene.control.Label;

import java.util.Random;

public class Game {
    private final String gameMode;
    private final String player1;
    private final String player2;
    private int whoseTurn;
    private final int whoStart;
    int counter;
    private final char[][] board = {{'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}};

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public int getCounter() { return counter; }

    public String getGameMode() {
        return gameMode;
    }

    public int getWhoStart() { return whoStart; }

    //local game
    public Game(String gameMode, String player1, String player2) {
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = player2;
        Random random = new Random();
        this.whoseTurn = 1 + random.nextInt(2);
        this.whoStart = this.whoseTurn;
        counter = 9;
        System.out.println(this.gameMode + " " + this.player1 + " " + this.player2 + " " + this.whoseTurn);
    }
    // game against computer
    public Game(String gameMode, String player1) {
        this.gameMode = gameMode;
        this.player1 = player1;
        this.player2 = "Komputer";
        Random random = new Random();
        this.whoseTurn = 1 + random.nextInt(2);
        this.whoStart = this.whoseTurn;
        counter = 9;
        System.out.println(this.gameMode + " " + this.player1 + " " + this.player2 + " " + this.whoseTurn);
    }


    public String click(String button, Label lChooseGameMod, String player, char sign1, char sign2, int turn){
        lChooseGameMod.setText("Kolej gracza: " + player + " - " + sign1);
        board[button.charAt(5) - 48][button.charAt(6) - 48] = sign2;
        this.whoseTurn = turn;
        counter--;
        System.out.println(this.whoseTurn);
        return String.valueOf(sign2);
    }


    public void ComputerMove(int[] result) {
        Boolean moveWasMade = false;
        int[] resultX = {0, 0, 0, 0, 0, 0, 0};
        int[] resultY = {0, 0, 0, 0, 0, 0, 0};
        win('x', resultX);
        win('o', resultY);

        if (this.board[1][1] == '_') {
            result[0] = 1;
            result[1] = 1;
        }
        else {
            Random random = new Random();
            int corner = 0;
            do {
                corner = 1 + random.nextInt(4);
                System.out.println("Los: " + corner);
                if (corner == 1 && this.board[0][0] == '_') {
                    moveWasMade = true;
                    result[0] = 0;
                    result[1] = 0;
                }
                if (corner == 2 && this.board[0][2] == '_') {
                    moveWasMade = true;
                    result[0] = 0;
                    result[1] = 2;
                }
                if (corner == 3 && this.board[2][0] == '_') {
                    moveWasMade = true;
                    result[0] = 2;
                    result[1] = 0;
                }
                if (corner == 4 && this.board[2][2] == '_') {
                    moveWasMade = true;
                    result[0] = 2;
                    result[1] = 2;
                }

            } while (!moveWasMade && (this.board[0][0] == '_' || this.board[0][2] == '_' || this.board[2][0] == '_' || this.board[2][2] == '_'));
            if (whoStart == 1) blockOrAttack(result, resultY, resultX);
            else if (whoStart == 2) blockOrAttack(result, resultX, resultY);
        }
    }


    public void blockOrAttack(int[] result, int[] attack, int[] block){
        if (attack[0] == 2) {
            moveAnalisys(result, attack);
        } else if (block[0] == 2) {
            moveAnalisys(result, block);
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.board[i][j] == '_') {
                        result[0] = i;
                        result[1] = j;
                    }
                }
            }
        }

    }


    public void moveAnalisys(int[] result, int[] operation){
        if (this.board[operation[1]][operation[2]] == '_') {
            result[0] = operation[1];
            result[1] = operation[2];
        } else if (this.board[operation[3]][operation[4]] == '_') {
            result[0] = operation[3];
            result[1] = operation[4];
        } else if (this.board[operation[5]][operation[6]] == '_') {
            result[0] = operation[5];
            result[1] = operation[6];
        }

    }


    //movement of one of the players
    public String move(String button, Label lChooseGameMod){
        if(this.gameMode.equals("Gra lokalna")){
            if(this.whoStart == 1) {
                if(this.whoseTurn == 1){
                    return click(button, lChooseGameMod, this.player2,'o', 'x', 2);
                }
                else {
                    return click(button, lChooseGameMod, this.player1,'x', 'o', 1);
                }
            }
            else {
                if(this.whoseTurn == 2) {
                    return click(button, lChooseGameMod, this.player1,'o', 'x', 1);
                }
                else {
                    return click(button, lChooseGameMod, this.player2,'x', 'o', 2);
                }
            }
        }else{
            if(this.whoStart == 1) {
                if(this.whoseTurn == 1){
                    return click(button, lChooseGameMod, this.player2,'o', 'x', 2);
                }
                else {
                    return click(button, lChooseGameMod, this.player1,'x', 'o', 1);
                }
            }
            else {
                if(this.whoseTurn == 2) {
                    return click(button, lChooseGameMod, this.player1,'o', 'x', 1);
                }
                else {
                    return click(button, lChooseGameMod, this.player2,'x', 'o', 2);
                }
            }
        }


    }



    //checking the win
    public int[] win(char sign, int[] result){
        int busy = 0;
        int free = 3;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                result[2 * (j + 1) - 1] = i;
                result[2 * (j + 1)] = j;
                if(this.board[i][j] == sign) {
                    busy++;
                    result[2 * (j + 1) - 1] = i;
                    result[2 * (j + 1)] = j;
                }
                if(this.board[i][j] != '_') free--;
                if(busy == 3) {
                    result[0] = 1;
                    return result;
                }
            }
            if(busy == 2 && free == 1) {
                result[0] = 2;
                return result;
            }
            busy = 0;
            free = 3;
        }
        for (int x : result) {
            x = 0;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                result[2 * (j + 1) - 1] = j;
                result[2 * (j + 1)] = i;
                if(this.board[j][i] == sign) {
                    busy++;
                    result[2 * (j + 1) - 1] = j;
                    result[2 * (j + 1)] = i;
                }
                if(this.board[j][i] != '_') free--;
                if(busy == 3) {
                    result[0] = 1;
                    return result;
                }
            }
            if(busy == 2 && free == 1) {
                result[0] = 2;
                return result;
            }
            busy = 0;
            free = 3;
        }

        for (int x : result) {
            x = 0;
        }
        for(int i = 0; i < 3; i++){
            result[2 * (i + 1) - 1] = i;
            result[2 * (i + 1)] = i;
            if(this.board[i][i] == sign) {
                busy++;
                result[2 * (i + 1) - 1] = i;
                result[2 * (i + 1)] = i;
            }
            if(this.board[i][i] != '_') free--;
            if(busy == 3)
            {
                result[0] = 1;
                return result;
            }
        }
        if(busy == 2 && free == 1) {
            result[0] = 2;
            return result;
        }
        busy = 0;
        free = 3;
        for (int x : result) {
            x = 0;
        }
        for(int i = 0; i < 3; i++){
            result[2 * (i + 1) - 1] = i;
            result[2 * (i + 1)] = 2 - i;
            if(this.board[i][2-i] == sign) {
                busy++;
                result[2 * (i + 1) - 1] = i;
                result[2 * (i + 1)] = 2 - i;
            }
            if(this.board[i][2 - i] != '_') free--;
            if(busy == 3) {
                result[0] = 1;
                return result;
            }
        }
        if(busy == 2 && free == 1) {
            result[0] = 2;
            return result;
        }
        for (int x : result) {
            x = 0;
        }
        return result;
    }


}
