package util;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Multiset implementation back by a {@link HashMap}. Inspiration from:
 * https://google.github.io/guava/releases/16.0/api/docs/com/google/common/collect/HashMultiset.html#remove(java.lang.Object,%20int)
 *
 * @param <E> type of element contained in multiset
 * @author Evan Quan
 */
public class Multiset<E> implements Iterable<E> {

    /**
     * All elements are contained in HashMap where E tracks the element, and
     * Integer tracks the count of the element
     */
    private HashMap<E, Integer> elements;

    /**
     * Constructs an empty multiset
     */
    public Multiset() {
        this.elements = new HashMap<>();
    }

    /**
     * Description copied from class: {@link java.util.AbstractCollection}
     * <p>
     * Ensures that this multiset contains the specified element (optional
     * operation). Returns true if this multiset changed as a result of the
     * call. (Returns false if this multiset does not permit duplicates and
     * already contains the specified element.)
     * </p><p>
     * Collections that support this operation may place limitations on what
     * elements may be added to this multiset. In particular, some multiset will
     * refuse to add null elements, and others will impose restrictions on the
     * type of elements that may be added. Collection classes should clearly
     * specify in their documentation any restrictions on what elements may be
     * added.
     * </p><p>
     * If a multiset refuses to add a particular element for any reason other
     * than that it already contains the element, it must throw an exception
     * (rather than returning false). This preserves the invariant that a
     * multiset always contains the specified element after this call returns.
     * </p>
     *
     * @param element element whose presence in this multiset is to b ensured
     * @return true if this multiset changed as a result of the call
     */
    public boolean add(E element) {
        if (this.contains(element)) {
            return false;
        }
        this.add(element, 1);
        return true;
    }

    /**
     * Add count number of elements to multiset
     *
     * @param element     to add
     * @param occurrences of element to add
     * @return the count of the element before the operation; possibly zero
     */
    public int add(E element, int occurrences) {
        int oldCount = this.count(element);
        int newCount = this.count(element) + occurrences;
        if (newCount > 0) {
            this.elements.put(element, newCount);
        } else {
            this.elements.remove(element);
        }
        return oldCount;
    }

    /**
     * Adds all of the elements in the specified multiset to this multiset
     * (optional operation). The behavior of this operation is undefined if the
     * specified multiset is modified while the operation is in progress. (This
     * implies that the behavior of this call is undefined if the specified
     * multiset is this multiset, and this multiset is nonempty.) This
     * implementation iterates over the specified multiset, and adds each object
     * returned by the iterator to this multiset, in turn.
     *
     * @param other multiset containing elements to be added to this multiset
     * @return true if this multiset changed as a result of the call
     */
    public boolean add(Multiset<E> other) {
        if (other.isEmpty()) {
            return false;
        }
        for (E element : other) {
            int occurrences = other.count(element);
            this.add(element, occurrences);
        }
        return true;
    }

    /**
     * Removes all of the elements from this multiset (optional operation)
     *
     * @return true if this multiset changed as a result of the call
     */
    public boolean clear() {
        if (elements.isEmpty()) {
            return false;
        }
        this.elements.clear();
        return true;
    }

    /**
     * Return shallow copy of this multiset
     */
    @Override
    public Multiset<E> clone() {
        Multiset<E> clone = new Multiset<E>();
        clone.add(this);
        return clone;
    }

    /**
     * Checks if multiset contains at least one of the specified element
     *
     * @param element - to check if multiset contains it
     * @return true if this multiset contains the specified element
     */
    public boolean contains(E element) {
        return this.elements.containsKey(element);
    }

    /**
     * Checks if the multiset is a superset of other multiset
     *
     * @param other - multiset to compare if this multiset contains it
     * @return true if this multiset contains all elements of equal or greater
     * number of occurrences of the other multiset
     */
    public boolean contains(Multiset<E> other) {
        boolean doesContain = true;
        for (E element : other) {
            if (this.count(element) < other.count(element)) {
                doesContain = false;
            }
        }
        return doesContain;
    }

    /**
     * Returns the number of occurrences of an element in this multiset (the
     * count of the element).
     *
     * @param element - the element to count occurrences of
     * @return the number of occurrences of the element in this multiset;
     * possibly zero but never negative
     */
    public int count(E element) {
        if (this.elements.containsKey(element)) {
            return this.elements.get(element);
        } else {
            return 0;
        }
    }

    /**
     * Checks that this and another multiset contain the same contents
     *
     * @param other - multiset to compare
     * @return true if this and another multiset contain the same contents
     */
    public boolean equals(Multiset<E> other) {
        // First check that both sets contain the same number of element types
        if (this.getElementCount() != other.getElementCount()) {
            return false;
        }
        // Next make sure that the number of occurrences of each type is the same
        for (E element : this.elements.keySet()) {
            if (this.count(element) != other.count(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comparing with some other non-multiset object automatically does not
     * equal this multiset
     *
     * @param other - object
     * @return false
     */
    @Override
    public boolean equals(Object other) {
        return false;
    }

    /**
     * @return the total number of element types contained in this multiset
     */
    public int getElementCount() {
        return this.elements.size();
    }

    /**
     * @return the total number of occurrences of all elements contained in this
     * multiset
     */
    public int getOccurrenceCount() {
        int total = 0;
        for (E element : elements.keySet()) {
            total += this.count(element);
        }
        return total;
    }

    /**
     * @return true if multiset is empty
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Description copied from class: {@link java.util.AbstractCollection}
     * <p>
     * Returns an iterator over the elements contained in this multiset
     *
     * @return an iterator over the elements contained in this multiset
     */
    @Override
    public Iterator<E> iterator() {
        return elements.keySet().iterator();
    }

    /**
     * Removes a single instance of the specified element from this multiset, if
     * it is present (optional operation).
     *
     * @param element
     */
    public int remove(E element) {
        return remove(element, 1);
    }

    /**
     * Remove count number of elements
     *
     * @param element     - the element to conditionally remove occurrences of
     * @param occurrences - the number of occurrences of the element to remove.
     *                    May be zero, in which no change will be made
     * @return the count of elements before the operation; possibly zero
     */
    public int remove(E element, int occurrences) {
        return this.add(element, -occurrences);
    }

    /**
     * Remove all occurrences of all elements of other multiset from this
     * multiset
     *
     * @param other - multiset
     * @return true if multiset has changed as a result of the call
     */
    public boolean remove(Multiset<E> other) {
        boolean hasChanged = false;
        for (E element : other) {
            if (this.remove(element, other.count(element)) > 0) {
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    /**
     * Remove all occurrences of a specified element from this multise
     *
     * @param element - the element to conditionally remove all occurrences of
     * @return true if multiset changed a result of the call
     */
    public boolean removeAll(E element) {
        return this.elements.remove(element) != null;
    }

    /**
     * Retains all and only the occurrences of a specified element from this
     * multiset. In other words, remove from this multiset all of its
     * occurrences that are not the specified element.
     *
     * @param element - the element to conditionally retain all occurrences of
     * @return true if multiset changed as a result of the call
     */
    // @Unimplemented
    public boolean retainAll(E element) {
        int occurrences = this.count(element);
        boolean containedOtherElements = this.getElementCount() > 1;
        this.clear();
        this.add(element, occurrences);
        return containedOtherElements && (occurrences > 0);
    }

    /**
     * Adds or removes the necessary occurrences of an element such that the
     * elements attains the desired count.
     *
     * @param element the element to add or remove occurrences of; may be null
     * @param count   the desired count of the element in this multiset
     * @return the count of the element before the operation; possibly zero
     */
    public int setCount(E element, int count) {
        int occurrences = this.count(element);
        if (count != occurrences) {
            return this.add(element, count - occurrences);
        }
        return occurrences;
    }

    /**
     * String representation of this multiset. The string representation
     * consists of a list of the multiset's elements.
     */
    @Override
    public String toString() {
        return "[ Multiset | Types: " + this.getElementCount() + " | Elements: " + this.getOccurrenceCount() + " | "
                + this.elements.toString() + " ]";
    }
}
