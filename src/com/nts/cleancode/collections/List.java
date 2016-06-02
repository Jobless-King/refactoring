package com.nts.cleancode.collections;

public class List extends AbstractList {
	private Object[] elements = new Object[10];
	private int size = 0;
	private boolean readOnly;

	public boolean isEmpty() {
		return size == 0;
	}


	public void add(Object element) {
		if (readOnly) /* 문제 제기 : 조건문 안에 !표현을 오독하기 쉽다. 해결 : 바로 파악할 수 있도록 처리했다 */
			return;
		if (shouldGrow()) { /* 문제 제기 : if문의 가독성이 떨어진다. 해결 : inline, Extract method*/
			grow();/* 문제 제기 : 한눈에 의도를 파악하기 어렵다 . 해결 : Extract method */
		}
		addElement(element);/*문제제기 : 한눈에 의도를 파악하기 어렵다. 해결 : Extract method */
	}

	void addElement(Object element) {
		elements[size++] = element;
	}

	void grow() {
		Object[] newElements =
            new Object[elements.length + 10];
		for (int i = 0; i < size; i++)
            newElements[i] = elements[i];
		elements = newElements;
	}

	boolean shouldGrow() {
		return size + 1 > elements.length;
	}

	public boolean contains(Object element) {
		for (int i=0; i<size; i++)
			if (elements[i].equals(element))
				return true;
		return false;
	}

	public int size() {
		return size;
	}

	public boolean remove(Object element) {
		if (readOnly)
			return false;
		else 	
			for (int i = 0; i < size; i++)
				if (elements[i].equals(element)) {
					elements[i] = null;
					Object[] newElements = new Object[size - 1];
					int k = 0;
					for (int j = 0; j < size; j++) {
						if (elements[j] != null)
							newElements[k++] = elements[j];
					}
					size--;
					elements = newElements;
					return true;
				}
		return false;
	}
	
	public Object get(int i) {
		return elements[i];
	}

	public int capacity() {
		return elements.length;
	}

	public void set(int i, Object value) {
		if (!readOnly) {
			if (i >= size)
				throw new ArrayIndexOutOfBoundsException();
			elements[i] = value;
		}
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}
}
