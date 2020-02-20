package ua.prozorro.gson;

public interface DataParser<T> {
	T parse(String JSON);
}
