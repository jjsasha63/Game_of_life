package com.red.prr1;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;


public class Board extends VBox {

    private Button next;
    private Canvas canvas;
    private Affine affine;
    private int drawMode = 1;
    private int width = 40;
    private int heigth = 40;

    private Simulation simulation;


    public Board() {

        next = new Button("next");
        next.setMaxSize(500,10);
        this.next.setOnAction(actionEvent -> {
                    simulation.step();
                    out();
        });


        canvas = new Canvas(500,500);
        this.canvas.setOnMousePressed(this::handleDraw);
        this.canvas.setOnMouseDragged(this::handleDraw);

        this.setOnKeyPressed(this::onKeyPressed);

        this.getChildren().addAll(this.next,this.canvas);
        this.affine = new Affine();
        this.affine.appendScale(500 / heigth, 500 / width);
        this.simulation = new Simulation(width,heigth);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.D) {
            this.drawMode = 1;
            System.out.println("Draw");
        } else if (keyEvent.getCode() == KeyCode.E) {
            this.drawMode = 0;
            System.out.println("Erase");
        }
    }

    private void handleDraw(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();
        try {
            Point2D simCoord = this.affine.inverseTransform(mouseX, mouseY);
            int simX = (int) simCoord.getX();
            int simY = (int) simCoord.getY();
            this.simulation.set(simX, simY, drawMode);
            out();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Error");
        }
    }

    public void out() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setTransform(this.affine);

        g.setFill(Color.LIGHTGRAY);
        g.fillRect(0, 0, 450, 450);

        g.setFill(Color.BLACK);
        for (int x = 0; x < this.simulation.width; x++) {
            for (int y = 0; y < this.simulation.height; y++) {
                if (this.simulation.isAlive(x,y)) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        g.setStroke(Color.GRAY);
        g.setLineWidth(0.05);
        for (int x = 0; x <= this.simulation.width; x++) {
            g.strokeLine(x, 0, x, heigth);
        }

        for (int y = 0; y <= this.simulation.height; y++) {
            g.strokeLine(0, y, width, y);
        }
    }
    }
