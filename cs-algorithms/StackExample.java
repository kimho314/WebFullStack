import java.util.ArrayList;

public class StackExample {
    public static void main(String[] args) {
        SimpleStack<Integer> simpleStack = new SimpleStack<>();
        simpleStack.push(1);
        simpleStack.push(2);
        simpleStack.push(3);
        while (simpleStack.size() > 0) {
            System.out.println(simpleStack.pop());
        }
    }

    public static class SimpleStack<E> {
        private ArrayList<E> elems;

        public SimpleStack() {
            this.elems = new ArrayList<>();
        }

        public boolean push(E elem) {
            return this.elems.add(elem);
        }

        public E pop() {
            if (this.elems.isEmpty()) {
                return null;
            }
            return this.elems.remove(this.elems.size() - 1);
        }


        public int size() {
            return this.elems.size();
        }
    }
}
