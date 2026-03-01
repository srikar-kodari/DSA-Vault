public class ImpNodeLL {    // Linked List Implementation
    
    int data;
    ImpNodeLL next;

    ImpNodeLL(int data) {
        this.data = data;
        this.next = null;
    }

    ImpNodeLL(int data, ImpNodeLL next) {
        this.data = data;
        this.next = next;
    }

}
