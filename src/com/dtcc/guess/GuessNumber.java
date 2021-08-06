package com.dtcc.guess;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuessNumber extends Application {
    int number = (int) (Math.random()*100 + 1);
    int guess=0;
    int count = 0;

    public void start(Stage s) {
        System.out.println("The correct guess would be "+number);
        s.setTitle("Guess Number");

        //TOP LABEL
        Label lblTop=new Label("GUESS NUMBER");
        lblTop.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));

        //LEFT LABEL
        VBox vboxLeft=new VBox(5);
        Label lblLeft=new Label("Attempts");
        TextField txtLeft=new TextField("0");
        txtLeft.setEditable(false);
        txtLeft.setPrefSize(10,10);
        vboxLeft.getChildren().addAll(lblLeft,txtLeft);


        // Create the Text Nodes
        VBox vboxCenter=new VBox(25);
        TextField txtGuess=new TextField("Enter your guess here.");
        Insets insets=new Insets(20,20,20,20);
        txtGuess.setPrefSize(10,10);
        Label lblMessage=new Label("");
        vboxCenter.setPadding(insets);
        vboxCenter.getChildren().addAll(txtGuess,lblMessage);

        //Right NODE
        Text rightText = new Text("");

        //BOTTOM NODE
        HBox hboxBottom=new HBox(10);
        hboxBottom.setSpacing(10);
        Button btnOk=new Button("OK");
        Button btnCancel=new Button("Cancel");
        hboxBottom.getChildren().addAll(btnOk,btnCancel);

        // Set the alignment of the Top Text to Center
        BorderPane.setAlignment(lblTop, Pos.TOP_CENTER);
        // Set the alignment of the Bottom Text to Center
        BorderPane.setAlignment(hboxBottom,Pos.BOTTOM_CENTER);
        // Set the alignment of the Left Text to Center
        BorderPane.setAlignment(vboxLeft,Pos.CENTER_LEFT);
        // Set the alignment of the Right Text to Center
        BorderPane.setAlignment(rightText,Pos.CENTER_RIGHT);
        BorderPane.setAlignment(vboxCenter,Pos.CENTER);

        BorderPane pane=new BorderPane(vboxCenter, lblTop, rightText, hboxBottom, vboxLeft);
        pane.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        btnCancel.setOnAction(e->{          //Event on Cancel button
            lblMessage.setText("");
            txtLeft.setText("0");
            txtGuess.setText("Enter your guess here.");
        });

        EventHandler<ActionEvent>textEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txtGuess.setText("");
            }
        };
        txtGuess.setOnMouseClicked(e -> { txtGuess.setText(""); });

        EventHandler<ActionEvent>okEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                    if(checkValidGuess(Integer.parseInt(txtGuess.getText().trim()),number,lblMessage,txtLeft,count)){
                        count=0;
                        txtLeft.setText("0");
                        number =(int) (Math.random()*100 + 1);
                        txtGuess.setText("Enter your guess here.");
                        System.out.println("The correct guess would be "+number);
                    }else{
                        count++;
                        txtLeft.setText(String.valueOf(count));

                    }
            }
        };

        btnOk.setOnAction(okEvent);  //Event on OK button

        Scene scene=new Scene(pane,350,250);
        s.setScene(scene);
        s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static boolean checkValidGuess(int guess, int number, Label lblMessage,TextField txtLeft,int count){
        int actualTry=count+1;
        if(guess>number){
            lblMessage.setText("Your guess is too high. Please try again");
            return false;
        }
        else if(guess<number){
            lblMessage.setText("Your guess is too low. Please try again");
            return false;
        }
        else{
            lblMessage.setText("Bingo!!! Your guess is correct.\nYou took "+actualTry+" guesses.");
            return true;
        }
    }
}
