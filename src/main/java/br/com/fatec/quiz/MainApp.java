package br.com.fatec.quiz;

import br.com.fatec.quiz.model.Perguntas;
import br.com.fatec.quiz.model.Respostas;
import br.com.fatec.quiz.service.PerguntasService;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    private final PerguntasService perguntasService = new PerguntasService();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        retornaPerguntasERespostas();
        retornaPerguntasComRespostasCorretas();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }
    public void retornaPerguntasComRespostasCorretas(){
        List<Perguntas> findAllCorrect = perguntasService.findAllCorrect();
        System.out.println("##########      Printando as perguntas e respostas corretas     ##########");
        System.out.println("");
        for (Perguntas p : findAllCorrect) {
            System.out.println("Pergunta: "+p.getPergunta());
            System.out.println("Dificuldade: "+p.getDificuldade().getDificultade());
            System.out.println("Explicacao: "+p.getExplicacao());
            for (Respostas resposta : p.getResposta()) {
                System.out.println("Resposta correta : "+resposta.getResposta());
            }
            System.out.println("");
            System.out.println("##########################");
        }
    }
    
        public void retornaPerguntasERespostas(){
        List<Perguntas> todasPerguntasComRespostas = perguntasService.trazPerguntasERespostas();
        System.out.println("##########      Printando as perguntas e respostas     ##########");
        System.out.println("");
        for (Perguntas p : todasPerguntasComRespostas) {
            System.out.println("Pergunta: "+p.getPergunta());
            System.out.println("Dificuldade: "+p.getDificuldade().getDificultade());
            System.out.println("Explicacao: "+p.getExplicacao());
            int i = 0;
            String[] opcao ={"a","b","c","d"};
            for (Respostas resposta : p.getResposta()) {
                Integer indice = (resposta.getId() /4);
                System.out.println("indice : "+indice);
                System.out.println("Resposta "+opcao[i++]+" : "+resposta.getResposta());
            }
            System.out.println("");
            System.out.println("##########################");
        }
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
