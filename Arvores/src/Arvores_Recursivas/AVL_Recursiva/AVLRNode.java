class AVLRNode<T extends Comparable<T>> {

    private AVLRNode<T> left;
    private T info;
    private AVLRNode<T> right;
    private int balFact;

    public AVLRNode(T info){
        this.info = info;
        this.balFact = 0;
    }

    public AVLRNode<T> getLeft() {
        return this.left;
    }
    public void setLeft(AVLRNode<T> left) {
        this.left = left;
    }

    public T getInfo() {
        return this.info;
    }
    public void setInfo(T info) {
        this.info = info;
    }

    public AVLRNode<T> getRight() {
        return this.right;
    }
    public void setRight(AVLRNode<T> right) {
        this.right = right;
    }

    public int getBalFact() {
        return this.balFact;
    }
    public void setBalFact(int balFact) {
        this.balFact = balFact;
    }

}