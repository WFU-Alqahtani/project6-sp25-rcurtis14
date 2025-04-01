import java.util.Random;

// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for(int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1,r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        // FIXME
        Node curr = head;

        if (head == null) {
            return null;
        }
        int indexCounter = 0;
        while (curr.next!= null && indexCounter < index) {
            curr = curr.next;
            indexCounter++;
        }

        if (curr == head){
            return remove_from_head();
        }

        else if (curr == tail){
            tail.prev.next = null;
            tail = tail.prev;

        }
        else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.next = null;
            curr.prev = null;
        }
        size --;
        return curr.data;
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        // FIXME
        Node curr = head;
        Node previous = null;
        Node insertValue = new Node(x);
        int countIndex = 0;

        if (head == null) {
            head = insertValue;
            size++;
        }
        while(curr.next != null ) {
            if (countIndex == index) {
                break;
            }
            previous = curr;
            countIndex++;
            curr = curr.next;
        }

        if (curr == head) {

            insertValue.next = head;
            head.prev = insertValue;
            head = insertValue;
            size++;
        }
        else if (curr == tail){

            add_at_tail(x);
        }
        else {

            curr.prev.next = insertValue;
            curr.prev = insertValue;
            insertValue.next = curr;
            insertValue.prev = previous;
            size++;
        }


    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        // FIXME
        Node curr = head;
        Node first = head;
        int count2 = 0;
        int count1 = 0;
        while (curr.next != null && count2 < index2) {
            if (count2 < index1) {
                first = first.next;
                curr = curr.next;
                count1++;
                count2++;
            } else {
                curr = curr.next;
                count2++;
            }
        }
            Node temp2 = new Node(curr.data);//saves the data from index 2 node
            Node temp1 = new Node(first.data);
            insert_at_index(remove_from_index(count1), count2); // takes the node from count 1 and removes it appending it at count 2

            curr= head;
            int countFinal = 0;
            while (curr.data != temp2.data) {
                countFinal++;
                curr = curr.next;
            }
            insert_at_index(remove_from_index(countFinal), count1);




    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        // FIXME
        Node insertValue = new Node(data);
        if (head == null){
            head = insertValue;
            tail = insertValue;


        }
        else{
        tail.next = insertValue;
        insertValue.prev = tail;
        tail = insertValue;
        }
        size ++;
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        // FIXME
        Node curr = head;

        if (head ==null){
            return null;
        }
        if (head == tail){ //in the case that there is one element in the list
            head = null;
            tail = null;
            size = 0;
            return curr.data;
        }
        else {
            head = head.next;
            head.prev.next = null;
            head.prev = null;
        }
        size--;
        return curr.data;
    }

    // check to make sure the linked list is implemented correctly by iterating forwards and backwards
    // and verifying that the size of the list is the same when counted both ways.
    // 1) if a node is incorrectly removed
    // 2) and head and tail are correctly updated
    // 3) each node's prev and next elements are correctly updated
    public void sanity_check() {
        // count nodes, counting forward
        Node curr = head;
        int count_forward = 0;
        while (curr != null) {
            curr = curr.next;
            count_forward++;
        }

        // count nodes, counting backward
        curr = tail;
        int count_backward = 0;
        while (curr != null) {

            curr = curr.prev;
            count_backward++;

        }

        // check that forward count, backward count, and internal size of the list match
        if (count_backward == count_forward && count_backward == size) {
            System.out.println("Basic sanity Checks passed");
        }
        else {
            // there was an error, here are the stats
            System.out.println("Count forward:  " + count_forward);
            System.out.println("Count backward: " + count_backward);
            System.out.println("Size of LL:     " + size);
            System.out.println("Sanity checks failed");
            System.exit(-1);
        }
    }

    // print the deck
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            curr.data.print_card();
            if(curr.next != null)
                System.out.print(" -->  ");
            else
                System.out.println(" X");

            if (i % 7 == 0) System.out.println("");
            i = i + 1;
            curr = curr.next;
        }
        System.out.println("");
    }
}