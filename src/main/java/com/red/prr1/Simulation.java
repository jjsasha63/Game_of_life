package com.red.prr1;

public class Simulation {

    int width;
    int height;
    int[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new int[width][height];
    }

    public boolean isAlive(int x,int y){
        if(this.board[x][y] == 1) return true;
        else return false;
    }

    public int neighbours(int x, int y) {
        int count = 0;
        for(int i = -1;i < 2;i++){
            for(int j = -1;j < 2;j++){
                int temp_x = x+i,temp_y = y+j;
                if(x == 0 && i==-1) temp_x = width-1;
                if(x == width-1 && i==1) temp_x = 0;
                if(y == 0 && j==-1) temp_y = height-1;
                if(y == height-1 && j==1) temp_y = 0;
                if(this.board[temp_x][temp_y] == 1) count++;
            }
        }
        return count-1;
    }


    public void step() {
        int[][] newBoard = new int[width][height];
        int count = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                count = neighbours(i,j);
                if(isAlive(i,j)){
                    if(count == 2 || count == 3) newBoard[i][j] = 1;
                    else newBoard[i][j] = 0;
                } else if(count == 2){
                    newBoard[i][j] = 1;
                }
                }
            }
        this.board = newBoard;
    }


    public void set(int x, int y, int state) {
        this.board[x][y] = state;
    }


}