package co.tdude.COEN352Midterm1;

public class ReverseQueue {
    public void reverse(Queue queue)
    {
        Stack stack = new Stack();
        while(queue.length() > 0)
        {
            stack.push(q.dequeue());
        }
        while(!stack.length() > 0)
        {
            queue.enqueue(stack.pop());
        }
    }
}
