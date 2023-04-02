package com.kenny.tank.ui;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.Texture;
import com.kenny.tank.db.JdbcUtils;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
public class Main_menu extends FXGLMenu {
    //主菜单类，包含login system
    private final TranslateTransition tt;
    private final Pane defaultPane;
    private boolean iscorrect=false;

    public static String Username;
    public String Password="?";


    private boolean verify(String username, String password) {
        //检查数据库和输入的账号密码
        JdbcUtils jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
        String sql = "select count(1) count from `user` where username = ? and password = ?";
        List<String> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        Username=username;
        Password=password;
        try {
            return jdbcUtils.count(sql, params) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public Main_menu() {
        //设置主菜单
        super(MenuType.MAIN_MENU);
       Texture texture= FXGL.texture("MainMenu.png");
        Text text=new Text("All Star Battle");
        text.setFont(Font.font("Verdana", FontPosture.ITALIC,100));
        text.setX(924);
        text.setY(150);

        Button button1=new Button("Start new game");
        button1.getStyleClass().add("menu_button1");
        button1.setOnAction(event->{
            FXGL.getGameController().startNewGame();
        });
        Button button2=new Button("Exit");
        button2.getStyleClass().add("menu_button2");
        button2.setOnAction(event->{
            FXGL.getGameController().exit();
        });

        VBox vBox=new VBox(
                100,
                button1,
                button2
        );
        vBox.setLayoutX(1024);
        vBox.setLayoutY(500);
        vBox.setVisible(false);

        //设置登陆按钮，界面
        VBox loginBox = new VBox(25);//登录VBOX
        loginBox.setAlignment(Pos.CENTER_LEFT);
        loginBox.setLayoutX(1024);
        loginBox.setLayoutY(800);
        loginBox.setVisible(true);

        TextField loginBoxUsername = new TextField();//登录VBOX用户名
        loginBoxUsername.setId("inputText");
        loginBoxUsername.setPromptText("username");
        loginBoxUsername.setLayoutX(1024);
        loginBoxUsername.setLayoutY(500);
        loginBoxUsername.getStyleClass().add("menu_login_user");

        TextField loginBoxPassword = new TextField();//登录VBOX密码
        loginBoxPassword.setId("inputText");
        loginBoxPassword.setPromptText("password");
        loginBoxPassword.setLayoutX(1024);
        loginBoxPassword.setLayoutY(500);
        loginBoxPassword.getStyleClass().add("menu_login_password");

        Label loginBoxLabel = new Label();//登录VBOX错误提示label
        loginBoxLabel.setId("label");

        Button loginBtn = new Button();//登录VBOX的 登录按钮
        loginBtn.setId("loginBtn");
        loginBtn.setText("Login in");
        loginBtn.getStyleClass().add("menu_login_button");
        loginBtn.setLayoutX(1024);
        loginBtn.setLayoutY(500);
        loginBtn.setOnAction(event -> {
            if (verify(loginBoxUsername.getText(), loginBoxPassword.getText())) {
                //如果认证成功，可进入游戏
                loginBox.setVisible(false);
                vBox.setVisible(true);
                iscorrect=true;
                System.out.println("Welcome, "+Username);
                System.out.println("Your password is "+Password);
            } else {
                loginBoxLabel.setText("Incorrect username or password.");
            }
        });

        if(iscorrect){
            loginBox.setVisible(false);
        }else {
            loginBox.setVisible(true);
        }


        loginBox.getChildren().addAll(
                loginBoxUsername,
                loginBoxPassword,
                loginBoxLabel,
                loginBtn);

        Texture tankTexture = FXGL.texture("R-C.jpg");
        tt = new TranslateTransition(Duration.seconds(1), tankTexture);
        tt.setInterpolator(Interpolators.ELASTIC.EASE_OUT());
        tt.setFromX(172);
        tt.setFromY(252);
        tt.setToX(374);
        tt.setToY(252);
        tt.setOnFinished(e -> loginBox.setVisible(true));
        getContentRoot().getChildren().addAll(texture,vBox,text);
        defaultPane=new Pane(texture,loginBox,vBox);
        getContentRoot().getChildren().setAll(defaultPane);
    }

    public  boolean IsIkun(){
        //检查是否是ikun
        if(Username.equals("Ikun")){
            return true;
        }
        else {
            return false;
        }
    }



    @Override
    public void onCreate() {
        getContentRoot().getChildren().setAll(defaultPane);
        tt.play();
    }
}
