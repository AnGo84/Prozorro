package ua.prozorro.entity.mappers;


import java.util.List;

public abstract class AbstractListMapper<T, E> {

    public abstract List<E> convertToEntitiesList(List<T> objects);

    public abstract List<T> convertToObjectsList(List<E> entities);
}
