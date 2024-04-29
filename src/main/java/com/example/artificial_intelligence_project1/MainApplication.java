package com.example.artificial_intelligence_project1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class MainApplication extends Application {
    public static ArrayList<String> list = new ArrayList<>();
    final static State initialState = new State(3, 3, Position.LEFT, 0, 0);
    static int index = 0;
    Button goBtn = new Button("GO!");
    HBox boxLeft = new HBox();
    HBox boxRight = new HBox();
    Button back = new Button("BACK!");
    Stage STAGE;
    ImageView BOAT = new objects().boat;

    Label depth=new Label();
    @Override
    public void start(Stage stage) throws IOException {
        Label label = new Label("welcome to the Missionary And Canibals Game ");
        label.setLayoutX(320);
        label.setLayoutY(80);
        Group root = new Group();
        Button BFS = new Button("BreadthFirstSearch");
        Button DFS = new Button("DepthLimitedSearch");

        BFS.setFont(new javafx.scene.text.Font(26));
        DFS.setFont(new javafx.scene.text.Font(26));


        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#4CAF50"), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);
        BFS.setBackground(background);
        DFS.setBackground(background);
        HBox hbox = new HBox();
        hbox.getChildren().addAll(BFS, DFS);
        hbox.setLayoutY(300);
        hbox.setLayoutX(320);
        hbox.setSpacing(150);

        label.setStyle(
                "-fx-font-size: 30px; " +
                        "-fx-text-fill: #333; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-color: #333; " +
                        "-fx-border-width: 2px; " +
                        "-fx-padding: 10px; " +
                        "-fx-background-color: #e0e0e0;"
        );
        depth.setStyle(
                "-fx-font-size: 30px; " +
                        "-fx-text-fill: #333; " +
                        "-fx-font-weight: bold; " +
                        "-fx-border-color: #333; " +
                        "-fx-border-width: 2px; " +
                        "-fx-padding: 10px; " +
                        "-fx-background-color: #e0e0e0;"
        );

        BFS.setOnAction(event -> {
            Stage stage2 = new Stage();
            stage.close();
            list.clear();
            depth.setText("");
            index = 0;
            SearchExecutor executor = new SearchExecutor();
            executor.executeBFS(initialState);
            excuteSearch(stage2);
        });

        DFS.setOnAction(event -> {
            Stage stage1 = new Stage();
            stage.close();
            index = 0;
            depth.setText("");
            list.clear();
            SearchExecutor executor = new SearchExecutor();
            executor.executeDFS(initialState);
            excuteSearch(stage1);

        });


        root.getChildren().addAll(label, hbox);
        Scene scene = new Scene(root, 1200, 700, Color.LIGHTBLUE);
        stage.setScene(scene);
        stage.setTitle("MissionaryAndCanibals");
        stage.show();
    }

    private void excuteSearch(Stage stage) {
        Group root = new Group();
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#4CAF50"), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
        Background background = new Background(backgroundFill);
        HBox pics = new HBox();
        pics.getChildren().addAll(boxLeft, boxRight);
        pics.setLayoutX(50);
        pics.setLayoutY(100);
        pics.setSpacing(500);
        goBtn.setLayoutY(280);
        goBtn.setLayoutX(350);
        goBtn.setFont(new javafx.scene.text.Font(26));
        back.setLayoutX(20);
        back.setLayoutY(400);
        depth.setLayoutY(400);
        depth.setLayoutX(550);

        back.setFont(new javafx.scene.text.Font(19));
        back.setBackground(background);
        goBtn.setBackground(background);
        depth.setText("DEPTH="+list.get(list.size()-1));

        goBtn.setOnAction(event -> {
//            System.out.println(list.get(2));
//            System.out.println(list.get(3));
            if (list.size() - 1 != index) {
                boxLeft.getChildren().clear();
                boxRight.getChildren().clear();
                String state[] = list.get(index).split("-");
                String boatPos = state[0];
                String leftSide = state[1].trim();
                String rightSide = state[2].trim();
                if (!leftSide.equals("")) {
                    String leftSplit[] = leftSide.split(",");

                    int size = Integer.parseInt(Character.toString(leftSplit[0].charAt(0)));
                    for (int i = 0; i < size; i++) {
                        if (leftSplit[0].charAt(1) == 'M') {
                            ImageView image = new objects().missionary;
                            boxLeft.getChildren().add(image);
                        }
                        if (leftSplit[0].charAt(1) == 'C') {
                            ImageView image = new objects().cannibal;
                            boxLeft.getChildren().add(image);
                        }
                    }
                    size = Integer.parseInt(Character.toString(leftSplit[1].charAt(0)));
                    for (int i = 0; i < size; i++) {
                        if (leftSplit[1].charAt(1) == 'M') {
                            ImageView image = new objects().missionary;
                            boxLeft.getChildren().add(image);
                        }
                        if (leftSplit[1].charAt(1) == 'C') {
                            ImageView image = new objects().cannibal;
                            boxLeft.getChildren().add(image);
                        }
                    }
                }
                if (!rightSide.equals("")) {
                    String rightSplit[] = rightSide.split(",");
                    int size = Integer.parseInt(Character.toString(rightSplit[0].charAt(0)));
                    for (int i = 0; i < size; i++) {
                        if (rightSplit[0].charAt(1) == 'M') {
                            ImageView image = new objects().missionary;
                            boxRight.getChildren().add(image);
                        }
                        if (rightSplit[0].charAt(1) == 'C') {
                            ImageView image = new objects().cannibal;
                            boxRight.getChildren().add(image);
                        }
                    }
                    size = Integer.parseInt(Character.toString(rightSplit[1].charAt(0)));
                    for (int i = 0; i < size; i++) {
                        if (rightSplit[1].charAt(1) == 'M') {
                            ImageView image = new objects().missionary;
                            boxRight.getChildren().add(image);
                        }
                        if (rightSplit[1].charAt(1) == 'C') {
                            ImageView image = new objects().cannibal;
                            boxRight.getChildren().add(image);
                        }
                    }
                }

                if(!boatPos.equals("")){
                    if(boatPos.equals("Left")){
                        BOAT.setLayoutX(300);
                        BOAT.setLayoutY(120);
                    }
                    else{
                        BOAT.setLayoutX(470);
                        BOAT.setLayoutY(120);
                    }
                }

                index++;

            }

        });
        back.setOnAction(event -> {
            list.clear();
            boxLeft.getChildren().clear();
            boxRight.getChildren().clear();
            pics.getChildren().clear();
            root.getChildren().clear();
            stage.close();
            Stage stage2 = new Stage();
            try {
                start(stage2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        BOAT.setLayoutY(10000);
        root.getChildren().addAll(pics, goBtn, back,BOAT,depth);
        Scene scene = new Scene(root, 900, 600, Color.LIGHTBLUE);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }


}