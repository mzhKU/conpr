package lecture.Stacker;

public class StackBaseClass {
    // 'top' points to next free field.
    int top = 0;
    int[] data;

    public StackBaseClass(int size) {
        this.data = new int[size];
    }
}
