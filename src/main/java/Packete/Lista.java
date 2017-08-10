package Packete;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

public class Lista<E> extends ArrayList<E>{
	
	public E head(){
		tirarErrorSiEstaVacia();
		return this.get(0);
	}
	private void tirarErrorSiEstaVacia(){
		if(this.isEmpty()){
			throw new NullPointerException();
		}
	}
	public E last(){
		tirarErrorSiEstaVacia();
		return this.get(lastIndex());
	}
	public Lista<E> tail(){
		tirarErrorSiEstaVacia();
		return (Lista<E>) this.subList(1,lastIndex()); //Untested
	}
	public Lista<E> drop(int cantidad){
		tirarErrorSiEstaVacia();
		if(cantidad > this.size()){
			throw new RuntimeException("No puedo sacar mas del size");
		}
		return (Lista<E>) this.subList(cantidad,lastIndex()); 
	}
	public Lista<E> take(int cantidad){
		if(cantidad > this.size()){
			throw new RuntimeException("No puedo sacar mas del size");
		}
		tirarErrorSiEstaVacia();
		return (Lista<E>) this.subList(0, cantidad); 
	}
	
	public E popLast(){
		tirarErrorSiEstaVacia();
		return this.remove(lastIndex());
	}
	public int sum(ToIntFunction<? super E> mapper){
		return this.stream().mapToInt(mapper).sum();
	}
	public double sum(ToDoubleFunction<? super E> mapper){
		return this.stream().mapToDouble(mapper).sum();
	}
	
	public E find(Predicate<? super E> predicate){
		if(!this.stream().anyMatch(predicate)){
			throw new NullPointerException();
		}
		return this.stream()
		.filter(predicate)
		.findFirst().get();
	}
	public long ocurrencesOf(E element){
		return this.stream().filter(x-> element==x).count();
	}
	
	public int lastIndex(){
		return this.size()-1;
	}
	
	
	
	
	
	
	
	
}
