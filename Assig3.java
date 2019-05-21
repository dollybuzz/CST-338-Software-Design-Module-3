/*public class Assig3
{

//Phase 1 Testing
   public static void main(String[] args)
   {
      Card cardOne = new Card('A', Card.Suit.HEARTS);
      Card cardTwo = new Card('2', Card.Suit.SPADES);
      Card cardThree = new Card('0', Card.Suit.DIAMONDS);
      System.out.println(cardOne.toString());
      System.out.println(cardTwo.toString());
      System.out.println(cardThree.toString());

      cardThree.set('8', Card.Suit.SPADES);
      cardOne.set('1', Card.Suit.CLUBS);

      System.out.println("\n" + cardOne.toString());
      System.out.println(cardTwo.toString());
      System.out.println(cardThree.toString());
   }*/

 /*
    //Phase 2 Testing
    public static void main(String[] args)
    {
        Card cardOne = new Card('A', Card.Suit.HEARTS);
        Card cardTwo = new Card('2', Card.Suit.SPADES);
        Card cardThree = new Card('J', Card.Suit.DIAMONDS);
        Card cardFour = new Card('K', Card.Suit.CLUBS);
        Card cardFive = new Card('6', Card.Suit.HEARTS);
        Hand firstHand = new Hand();

        int numCardsInCycle = 5; //can change this between 1-5 cards to test the switch statement
        int loops = (Hand.MAX_CARDS/numCardsInCycle);
        int loopExtraTimes = Hand.MAX_CARDS % numCardsInCycle;

        if(loopExtraTimes != 0) //Between the possibility of 1-5 cards, 3 cards in the cycle will warrant a remainder value
        {
            int count = 0;
            for(count = 0; count < loops; count++)
            {
                firstHand.takeCard(cardOne);
                firstHand.takeCard(cardTwo);
                firstHand.takeCard(cardThree);
                System.out.println(count);
            }
            count = loopExtraTimes;
            while(count != 0)
            {
                firstHand.takeCard(cardOne);
                count--;
            }
        }
        else
        {
            while(firstHand.getNumCards() != (Hand.MAX_CARDS))
            {
                switch(numCardsInCycle)
                {
                    case 1:
                        firstHand.takeCard(cardOne);
                        break;
                    case 2:
                        firstHand.takeCard(cardOne);
                        firstHand.takeCard(cardTwo);
                        break;
                    case 3:
                        firstHand.takeCard(cardOne);
                        firstHand.takeCard(cardTwo);
                        firstHand.takeCard(cardThree);
                        break;
                    case 4:
                        firstHand.takeCard(cardOne);
                        firstHand.takeCard(cardTwo);
                        firstHand.takeCard(cardThree);
                        firstHand.takeCard(cardFour);
                        break;
                    case 5:
                        firstHand.takeCard(cardOne);
                        firstHand.takeCard(cardTwo);
                        firstHand.takeCard(cardThree);
                        firstHand.takeCard(cardFour);
                        firstHand.takeCard(cardFive);
                        break;
                }
            }
        }
        if(firstHand.getNumCards() == Hand.MAX_CARDS)
        {
            System.out.println("Hand full");
        }
        System.out.println("After deal\n" + firstHand.toString());
        System.out.println("Testing inspectCard()");
        System.out.println(firstHand.playCard());
        System.out.println(firstHand.inspectCard(100)); 
        
        while(firstHand.getNumCards() != 0)
        {
            System.out.println("Playing " + firstHand.playCard());
        }
        if(firstHand.getNumCards() == 0)
        {
            firstHand.resetHand();
        }
        System.out.println("After playing all cards\n" + firstHand.toString());
    }
}*/

class Card
{
    public enum Suit{SPADES, DIAMONDS, HEARTS, CLUBS}

    private char value;
    private Suit suit; //instance of the enum Suit
    private boolean errorFlag;

    public Card()
    {
        value = 'A';
        suit = Suit.SPADES;
    }

    public Card(char value, Suit suit)
    {
        set(value, suit);
    }

    public String toString()
    {
        if (errorFlag)
        {
            return "** illegal **";
        }
        else
            return this.value + " of " + this.suit;
    }

    public boolean set(char value, Suit suit)
    {
        if (isValid(value, suit))
        {
            this.value = value;
            this.suit = suit;
            errorFlag = false;
            return true;
        }
        else
            errorFlag = true;
        return false;
    }

    public char getValue()
    {
        return this.value;
    }

    public Suit getSuit()
    {
        return this.suit;
    }

    public boolean isErrorFlag()
    {
        return this.errorFlag;
    }

    public boolean equals(Card card)
    {
        if (this.value == card.value && this.suit == card.suit)
            return true;
        else
            return false;
    }

    private boolean isValid(char value, Suit suit)
    {
        String cardValues = "A23456789TJQK";
        if(cardValues.indexOf(value) != -1)
        {
            return true;
        }
        else
            return false;
    }
}

class Hand
{
    public static final int MAX_CARDS = 100;
    private Card[] myCards;
    private static int numCards;

    public Hand()
    {
        myCards = new Card[MAX_CARDS];
        numCards = 0;
    }

    public void resetHand()
    {
        myCards = new Card[numCards];
    }

    public boolean takeCard(Card card)
    {
        myCards[numCards] = new Card(card.getValue(), card.getSuit());
        numCards += 1;
        return true;
    }

    public Card playCard()
    {
        numCards = numCards - 1;
        Card returnCard = myCards[numCards];
        return returnCard;
    }

    public String toString()
    {
        String hand = "Hand = (";
        for(int i = 0; i < numCards; i++)
        {
            if(i == numCards-1)
            {
                hand += myCards[i];
            }
            else
            {
                hand += myCards[i] + ", ";
            }
        }
        hand += ")";
        return hand;
    }

    public int getNumCards()
    {
        return numCards;
    }

    public Card inspectCard(int k)
    {
        if(k > numCards-1)
        {
            //Intentionally sets the card to a value that will cause errorFlag to return true
            Card myNewCards = new Card('0', Card.Suit.SPADES);
            return myNewCards;
        }
        Card myNewCards = myCards[k];
        return myNewCards;
    }
}
