package com.github.silverrka.intelljiblackjack.actions;

public class Card {
    private int cardValue;
    private int cardSuit;
    public Card()
    {
        cardValue = 0;
        cardSuit = 0;
    }
    public Card(int suit, int val)
    {
        cardValue = val;
        cardSuit = suit;
    }
    public void setValue(int val)
    {
        cardValue = val;
    }
    public void setSuit(int suit)
    {
        cardSuit = suit;
    }
    public int getValue()
    {
        return cardValue;
    }
    public int getSuit()
    {
        return cardSuit;
    }
    public int getY()
    {
        return 123*cardSuit;
    }
    public int getX()
    {
        return 79*(cardValue-1);
    }
}
