package br.com.fatec.quiz;

import br.com.fatec.quiz.model.Perguntas;
import br.com.fatec.quiz.service.PerguntasService;

import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Daniel Hideki
 */
public class MainApp extends Application {

    private final PerguntasService perguntasService = new PerguntasService();
    Integer indice = 0;
    List<Perguntas> todasPerguntasComRespostas;
    List<Perguntas> findAllCorrect;
    BorderPane border = new BorderPane();
    Text textoA = new Text();
    Text textoB = new Text();
    Text textoC = new Text();
    Text textoD = new Text();
    RadioButton respA;
    RadioButton respB;
    RadioButton respC;
    RadioButton respD;
    String respostaCorreta;
    Label explicacao = new Label();
    String explicacaoCorreta;
    Label correcao = new Label();
    Button buttonProximo;
    Button buttonResponder;
    Button buttonPular;
    Text titulo = new Text();
    String dificuldade;
    Integer score = 0;
    Integer pontos=0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        todasPerguntasComRespostas = perguntasService.trazPerguntasERespostas();
        findAllCorrect = perguntasService.findAllCorrect();
        
        HBox hbox = addHBoxButtonNavegation();
        border.setBottom(hbox);

        HBox hboxClose = addHBoxPlayAndClose();
        border.setTop(hboxClose);

        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 130));
        titulo.setText("Ask To Me");
        border.setCenter(titulo);

        buttonResponder.setDisable(true);
        buttonPular.setDisable(true);
        buttonProximo.setDisable(true);

        Scene scene = new Scene(border, 1300, 800);

        primaryStage.setTitle("Quizz");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox addVBoxPontuacao() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Text title = new Text("Score");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);
        Text pontuacaoText = new Text();
        pontuacaoText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        pontuacaoText.setFill(Color.DARKGREEN);
        pontuacaoText.setText(score.toString());

        vbox.getChildren().add(pontuacaoText);

        return vbox;
    }

    private HBox addHBoxButtonNavegation() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #330099");
        hbox.setAlignment(Pos.CENTER);
        buttonPular = new Button("Pular");
        buttonResponder = new Button("Responder");
        buttonProximo = new Button("Proximo");

        buttonPular.setPrefSize(100, 20);
        buttonPular.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonResponder.setDisable(true);
                buttonPular.setDisable(true);
                buttonProximo.setDisable(false);
                explicacao.setVisible(true);
                explicacao.setText(explicacaoCorreta);
            }
        });

        buttonResponder.setPrefSize(100, 20);
        buttonResponder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                buttonResponder.setDisable(true);
                buttonProximo.setDisable(false);
                buttonPular.setDisable(true);
                correcao.setVisible(true);
                correcao.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                explicacao.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                if (respA.isSelected()) {
                    if (textoA.getText().equals(respostaCorreta)) {
                        correcao.setTextFill(Color.BLUE);
                        correcao.setText("Resposta Correta!!!");
                        calculaPontos();
                    } else {
                        correcao.setTextFill(Color.RED);
                        correcao.setText("A resposta correta é: " + respostaCorreta);
                        explicacao.setVisible(true);
                        explicacao.setText(explicacaoCorreta);
                    }
                } else if (respB.isSelected()) {
                    if (textoB.getText().equals(respostaCorreta)) {
                        correcao.setTextFill(Color.BLUE);
                        correcao.setText("Resposta Correta!!!");
                        calculaPontos();
                    } else {
                        correcao.setTextFill(Color.RED);
                        correcao.setText("A resposta correta é: " + respostaCorreta);
                        explicacao.setVisible(true);
                        explicacao.setText(explicacaoCorreta);
                    }
                } else if (respC.isSelected()) {
                    if (textoC.getText().equals(respostaCorreta)) {
                        correcao.setTextFill(Color.BLUE);
                        correcao.setText("Resposta Correta!!!");
                        calculaPontos();
                    } else {
                        correcao.setTextFill(Color.RED);
                        correcao.setText("A resposta correta é: " + respostaCorreta);
                        explicacao.setVisible(true);
                        explicacao.setText(explicacaoCorreta);
                    }
                } else if (respD.isSelected()) {
                    if (textoD.getText().equals(respostaCorreta)) {
                        correcao.setTextFill(Color.BLUE);
                        correcao.setText("Resposta Correta!!!");
                        calculaPontos();
                    } else {
                        correcao.setTextFill(Color.RED);
                        correcao.setText("A resposta correta é: " + respostaCorreta);
                        explicacao.setVisible(true);
                        explicacao.setText(explicacaoCorreta);
                    }
                }
                VBox pontuacao = addVBoxPontuacao();
                border.setRight(pontuacao);
                
            }
        });

        buttonProximo.setPrefSize(100, 20);

        buttonProximo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                VBox vbox = addVBoxContent();
                border.setLeft(vbox);
                buttonResponder.setDisable(false);
                buttonPular.setDisable(false);
                
            }
        });

        hbox.getChildren().addAll(buttonPular, buttonResponder, buttonProximo);
        return hbox;
    }

    private void calculaPontos() {
            score += pontos;
    }

    private VBox addVBoxContent() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setAlignment(Pos.CENTER_LEFT);

        Text title = new Text("Pergunta");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        vbox.getChildren().add(title);

        Text nivel = new Text("Nivel de dificuldade");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 50));

        Text dificuldadeDaPergunta = new Text();
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label pergunta = new Label();

        VBox espaco1 = new VBox();
        espaco1.setPadding(new Insets(10));

        vbox.getChildren().addAll(nivel,
                dificuldadeDaPergunta,
                espaco1,
                pergunta);

        ToggleGroup group = new ToggleGroup();
        respA = new RadioButton("Resposta A: ");
        respB = new RadioButton("Resposta B: ");
        respC = new RadioButton("Resposta C: ");
        respD = new RadioButton("Resposta D: ");
        respA.setToggleGroup(group);
        respB.setToggleGroup(group);
        respC.setToggleGroup(group);
        respD.setToggleGroup(group);

        HBox linha1 = new HBox();
        linha1.getChildren().add(respA);
        linha1.getChildren().add(textoA);

        HBox linha2 = new HBox();
        linha2.getChildren().add(respB);
        linha2.getChildren().add(textoB);

        HBox linha3 = new HBox();
        linha3.getChildren().add(respC);
        linha3.getChildren().add(textoC);

        HBox linha4 = new HBox();
        linha4.getChildren().add(respD);
        linha4.getChildren().add(textoD);

        HBox espaco = new HBox();
        espaco.setPadding(new Insets(30));

        HBox linha5 = new HBox();
        linha5.getChildren().add(correcao);
        correcao.setVisible(false);
        correcao.setPrefSize(300, 200);
        correcao.setWrapText(true);

        HBox linha6 = new HBox();
        linha6.getChildren().add(explicacao);
        explicacao.setVisible(false);
        explicacao.setPrefSize(300, 200);
        explicacao.setWrapText(true);

        pergunta.setText(todasPerguntasComRespostas.get(indice).getPergunta());
        pergunta.setPrefSize(500, 100);
        pergunta.setWrapText(true);
        textoA.setText(todasPerguntasComRespostas.get(indice).getResposta().get(0).getResposta());
        textoB.setText(todasPerguntasComRespostas.get(indice).getResposta().get(1).getResposta());
        textoC.setText(todasPerguntasComRespostas.get(indice).getResposta().get(2).getResposta());
        textoD.setText(todasPerguntasComRespostas.get(indice).getResposta().get(3).getResposta());
        dificuldade = todasPerguntasComRespostas.get(indice).getDificuldade().getDificultade();
        pontos = todasPerguntasComRespostas.get(indice).getDificuldade().getPontos();
        dificuldadeDaPergunta.setText(dificuldade);
        vbox.getChildren().addAll(linha1, linha2, linha3, linha4, espaco, linha5, linha6);

        respostaCorreta = findAllCorrect.get(indice).getResposta().get(0).getResposta();
        explicacaoCorreta = findAllCorrect.get(indice).getExplicacao();
        indice++;
        buttonProximo.setDisable(true);
        return vbox;
    }

    private HBox addHBoxPlayAndClose() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #550099");
        hbox.setAlignment(Pos.CENTER);

        final Button buttonPlay = new Button("Play");
        buttonPlay.setPrefSize(100, 20);
        buttonPlay.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                VBox vbox = addVBoxContent();
                border.setLeft(vbox);

                VBox pontuacao = addVBoxPontuacao();
                border.setRight(pontuacao);

                buttonPlay.setDisable(true);
                titulo.setVisible(false);
                buttonResponder.setDisable(false);
                buttonPular.setDisable(false);
            }
        });
        Button buttonFechar = new Button("Fechar");
        buttonFechar.setPrefSize(100, 20);
        buttonFechar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
        hbox.getChildren().addAll(buttonPlay, buttonFechar);
        return hbox;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}