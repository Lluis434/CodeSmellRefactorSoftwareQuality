package org.example.controllers;

import org.example.studycards.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.controllers.MainController.getInput;
import static org.example.controllers.MainController.validateInput;

public class StudyCardsController {
    private FlashCard flashCard = new FlashCard("FlashCard");
    private LeitnerSystem leitnerSystem = new LeitnerSystem("LeitnerSystem");
    private CardManager manager = CardManager.getCardManager();
    private Map<String, Runnable> actions = new HashMap<>();

    public StudyCardsController() {
        assignActions();
    }

    public StudyCardsController(LeitnerSystem leitnerSystem) {
        assignActions();
        this.leitnerSystem = leitnerSystem;
    }

    void assignActions(){
        actions.put("1", this::handleViewCards);
        actions.put("2", this::handleCreateCard);
        actions.put("3", this::handleRemoveCard);
        actions.put("4", this::handleRandomFlashCard);
        actions.put("5", () -> leitnerSystem.promptAndInsertCard());
        actions.put("6", () -> leitnerSystem.promptAndRemoveCard());
        actions.put("7", () -> {
            try {
                leitnerSystem.promptAndUpgradeCard();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        actions.put("8", () -> {
            try {
                leitnerSystem.promptAndDowngradeCard();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        actions.put("9", this::handleViewBoxes);
        actions.put("10", this::handleGetRandomCardFromBox);
    }

    public void handleViewCards(){
        Map<Integer, Card> cards = manager.getCardsMap();
        List<Integer> keys = new ArrayList<>(cards.keySet());
        StringBuilder response = new StringBuilder();
        for(Integer key : keys){
            Card card = cards.get(key);
            response.append("[id: ").append(key).append("] Question: ").append(card.getQuestion())
                    .append(", Answer: ").append(card.getAnswer()).append("\n");
        }
        System.out.println(response.toString().isEmpty() ? "No cards" : response.toString());
    }

    public void handleRemoveCard(){
        System.out.println("Type card id:");
        int id = Integer.parseInt(getInput());
        manager.removeCard(id);
    }

    public void handleCreateCard(){
        System.out.println("Type the question: \n");
        String question = getInput();
        System.out.println("Type the answer: \n");
        String answer = getInput();
        manager.addCard(question, answer);
    }

    public void handleRandomFlashCard() {
        System.out.println("Random flash card:");
        Integer id = flashCard.randomFlashCard();
        System.out.println(manager.formatCard(id));
    }

    public void handleViewBoxes(){
        System.out.println(leitnerSystem.toString());
    }

    // Novo método que retorna a string do cartão aleatório de caixa
    public String getRandomCardFromBox() {
        try {
            return leitnerSystem.getFormattedRandomCardFromBoxes();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Método que exibe no console o cartão aleatório de caixa
    public void handleGetRandomCardFromBox() {
        System.out.println(getRandomCardFromBox());
    }

    public void handleCardsInput(){
        try{
            while(true){
                controllerOptions();
                String response = validateInput(actions);
                if(response == null) {return;}
                actions.get(response).run();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void controllerOptions(){
        System.out.println("""
                0 - return
                1 - view cards
                2 - create card
                3 - delete card
                4 - (FlashCard) Get random card
                5 - (Leitner) Insert card in box
                6 - (Leitner) Remove card from box
                7 - (Leitner) Upgrade card from box
                8 - (Leitner) Downgrade card from box
                9 - (Leitner) View boxes
                10- (Leitner) Get random card from box
               """);
    }
}
