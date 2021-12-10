public class Stack {
    static final int MAX = 1000;
    int top;
    String a[] = new String[MAX]; // Maximum size of Stack

    boolean isEmpty()
    {
        return (top < 0);
    }
    Stack()
    {
        top = -1;
    }

    boolean push(String x)
    {
        if (top >= (MAX - 1)) {
            return false;
        }
        else {
            a[++top] = x;
            return true;
        }
    }

    String pop()
    {
        if (top < 0) {
            return null;
        }
        else {
            String x = a[top--];
            return x;
        }
    }

    String peek()
    {
        if (top < 0) {
            return null;
        }
        else {
            String x = a[top];
            return x;
        }
    }

    void print(){
        for(int i = top;i>-1;i--){
            System.out.print(" "+ a[i]);
        }
    }
}
