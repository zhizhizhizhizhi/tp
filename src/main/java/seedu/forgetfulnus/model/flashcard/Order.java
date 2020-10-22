package seedu.forgetfulnus.model.flashcard;

/**
 * Tracks the order in which a FlashCard is added to the Glossary. Used for sorting and is updated when FlashCards are
 * deleted. No two unique FlashCards should have the same order at any point in time.
 */

public class Order implements Comparable<Order> {
    private int value;
    public Order(int value) {
        this.value = value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof Order && value == ((Order) other).getValue());
    }
    @Override
    public int compareTo(Order other) {
        return value - other.value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
