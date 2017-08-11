package Packete;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Lista<E> extends ArrayList<E>{
	
	public Lista (List<E> list){
		list.forEach(x->this.add(x));
	}
	
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
		return this.get(this.size()-1);
	}
	public Lista<E> tail(){
		tirarErrorSiEstaVacia();
		return new Lista<E>(this.subList(1,size())); //Untested
	}
	public Lista<E> drop(int cantidad){
		tirarErrorSiEstaVacia();
		if(cantidad > this.size()){
			throw new RuntimeException("No puedo sacar mas del size");
		}
		return new Lista<E>(this.subList(cantidad,this.size())); 
	}
	public Lista<E> take(int cantidad){
		if(cantidad > this.size()){
			throw new RuntimeException("No puedo sacar mas del size");
		}
		tirarErrorSiEstaVacia();
		return new Lista<E>(this.subList(0, cantidad)); 
	}
	
	public E popLast(){
		tirarErrorSiEstaVacia();
		return this.remove(this.size()-1);
	}
	public int sumInt(ToIntFunction<? super E> mapper){
		return this.stream().mapToInt(mapper).sum();
	}
	public double sumDouble(ToDoubleFunction<? super E> mapper){
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
	
	
	public boolean areEquals(List<E> lista) {
		if(lista.size() != this.size()) return false;
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i) != lista.get(i)) return false;
		}
		return true;
	}
	
	public Lista<E> filter(Predicate <? super E> predicate){
		List<E> lista = this.stream().filter(predicate).collect(Collectors.toList());;
		return new Lista<E>(lista);
	}
	
	public boolean any(Predicate <? super E> predicate) {
		return this.stream().anyMatch(predicate);
	}
	
	public boolean all(Predicate <? super E> predicate) {
		return this.stream().anyMatch(predicate);
	}
	
	
}
