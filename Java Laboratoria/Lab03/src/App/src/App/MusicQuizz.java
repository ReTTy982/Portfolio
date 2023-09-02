package App;

import DataHandler.JsonParser;
import RequestHandler.RequestBuilder;
import org.json.JSONException;

import javax.sound.midi.Soundbank;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MusicQuizz {
    MainWindow window;
    JsonParser jsonParser;
    RequestBuilder requestBuilder;
    Random random;
    String goodAnswer;
    String question;
    String answerID;
    String questionType;
    HashMap<String,List<String>> authorMap;
    HashMap<String,Integer> albumCountMap;
    HashMap<String,List<String>> songDateMap;
    List<String> openQuestionBtn;

    public void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer;
    }



    public MusicQuizz(MainWindow window) throws JSONException, IOException, InterruptedException, BadLocationException {
        this.requestBuilder = new RequestBuilder();
        this.jsonParser = new JsonParser(requestBuilder);
        this.random = new Random();
        this.window = window;
        requestBuilder.setFormat("json");
        authorMap = new HashMap<>();
        albumCountMap = new HashMap<>();
        String[] buttons = {"I naciśnij","Enter","Odpowiedź", "Wrowadź"};
        this.openQuestionBtn = Arrays.asList(buttons);
        prepQuestions();
    }

    private void prepQuestions() throws JSONException, IOException, InterruptedException, BadLocationException {
        getArtistQuestions();
        getSongDate();
        askQuestion();

    }

    private void askQuestion() throws BadLocationException {
        int x= random.nextInt(0,2);
        System.out.println("X : " + x );
        switch (x) {
            case 0:
                dateQuestion();
                break;
            case 1:
                artistQuestion();
                break;
        }

    }
    private void getArtistQuestions() throws IOException, InterruptedException, JSONException {
        int x = random.nextInt(1, 10000);

        String request = requestBuilder.builder(RequestBuilder.types.RECORDING, RequestBuilder.types.TAG, "rock", x, 100);
        String response = requestBuilder.sendRequest(request);
        authorMap = jsonParser.getRandomSongGenre(response);

    }

    private void getSongDate() throws IOException, JSONException, InterruptedException {
        int x = random.nextInt(1, 10000);

        String request = requestBuilder.builder(RequestBuilder.types.RECORDING, RequestBuilder.types.TAG, "rock", x, 100);
        System.out.println(request);

        String response = requestBuilder.sendRequest(request);
        songDateMap = jsonParser.getSongDate(response);

    }


    private void getAlbumCount() throws IOException, InterruptedException, JSONException {
        List<String> ids = new ArrayList<>();
        int x  = random.nextInt(1,1000);
        String request = requestBuilder.builder(RequestBuilder.types.ARTIST,RequestBuilder.types.TAG,"rock",x,100);
        String response = requestBuilder.sendRequest(request);
        ids = jsonParser.getID(response);
        for (String id : ids){
            request = requestBuilder.builder(RequestBuilder.types.RELEASE,RequestBuilder.types.ARTIST, id, 0);
            response = requestBuilder.sendRequest(request);
            System.out.println(request);

            albumCountMap.put(id, jsonParser.getAlbumCount(response));
        }
        System.out.println(albumCountMap);



    }




    public void artistQuestion() throws BadLocationException {
        questionType = "author";
        List<String> keys = new ArrayList<>(authorMap.keySet());
        List<String> answers = new ArrayList<>();
        answerID = keys.get(random.nextInt(keys.size()));
        goodAnswer = authorMap.get(answerID).get(1);
        answers.add(goodAnswer);
        while (answers.size() < 4) {
            String potentialAnswer = authorMap.get(keys.get(random.nextInt(keys.size()))).get(1);
            if (!answers.contains(potentialAnswer))
                answers.add(potentialAnswer);
        }
        question = "Kto jest autorem utworu: " + authorMap.get(answerID).get(0) + "\n\n";
        window.addText(question);
        Collections.shuffle(answers);
        System.out.println(goodAnswer);
        window.setButtons(answers);
        window.setQuestionType("closed");
    }

    public void dateQuestion() throws BadLocationException {
        questionType = "date";
        List<String> keys = new ArrayList<>(songDateMap.keySet());
        answerID = keys.get(random.nextInt(keys.size()));

        goodAnswer = songDateMap.get(answerID).get(1);
        question = "Kiedy wydano : " + songDateMap.get(answerID).get(0) + "\n\n";
        window.addText(question);
        System.out.println(goodAnswer);
        window.setButtons(openQuestionBtn);
        window.setQuestionType("open");

    }


    public void questionChecker(String answer) throws IOException, InterruptedException, JSONException, BadLocationException {
        if (questionType == "author"){
            String request = requestBuilder.builder(RequestBuilder.types.RECORDING,RequestBuilder.types.ID,answerID,0,1);
            String response = requestBuilder.sendRequest(request);
            String jsonAnswer = jsonParser.getAuthorByID(response);
            if(!jsonAnswer.equals(answer)){
                window.addText("Nie, poprawna odpowiedź to nie: " + answer + "\n" + "Poprawna odpowiedź to: " + jsonAnswer + "\n");
            }
            else {
                window.addText("Tak, poprawna odpowiedź to : " + jsonAnswer + "\n" + "TESTOWNAIE" + " " + jsonAnswer + " " + goodAnswer);
            }

        }
        else if (questionType == "date"){
            String request = requestBuilder.builder(RequestBuilder.types.RECORDING,RequestBuilder.types.ID,answerID,0,1);
            System.out.println(request);
            String response = requestBuilder.sendRequest(request);
            String jsonAnswer = jsonParser.getSongByID(response);
            if(!jsonAnswer.equals(answer)){
                window.addText("Nie, poprawna odpowiedź to nie rok: " + answer + "\n" + "Poprawna odpowiedź to rok: " + jsonAnswer + "\n");
            }
            else{
                window.addText("Tak, poprawna odpowiedź to: " + jsonAnswer+"\n");
            }
        }
        askQuestion();

    }




}
