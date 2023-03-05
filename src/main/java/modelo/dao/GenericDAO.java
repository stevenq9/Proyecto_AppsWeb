package modelo.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
	public void create(T entity);
	public void update(T entity);
	public T getById(ID id);
	public List<T> getAll();
}
