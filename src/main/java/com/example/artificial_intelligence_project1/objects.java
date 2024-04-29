package com.example.artificial_intelligence_project1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class objects {
    ImageView cannibal = new ImageView(new Image("file:cannibal.PNG"));

    ImageView missionary = new ImageView(new Image("file:missionary.PNG"));
    ImageView boat = new ImageView((new Image("file:boat.PNG")));

    public objects() {
        cannibal.setFitHeight(100);
        cannibal.setFitWidth(40);
        missionary.setFitHeight(100);
        missionary.setFitWidth(40);
        boat.setFitWidth(80);
        boat.setFitHeight(110);

    }
}
