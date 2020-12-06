package action;

public interface Exec<T,V> {
	T exec(V v);
}
