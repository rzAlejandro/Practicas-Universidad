public interface Lock {
    public abstract void takeLock(int i);
    public abstract void releaseLock(int i);
}