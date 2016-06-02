package com.nts.cleancode.collections;

public class List extends AbstractCollection{ /* 문제제기 : 필요없는 추상클래스를 제거 하려 한다. 해결방법 : 상위 클래스와의 의존성을 제거*/

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(Object element) {
		for (int i=0; i<size; i++)
			if (get(i).equals(element))
				return true;
		return false;
	}

	public boolean remove(Object element) {
		if (readOnly)
			return false;
		else 	
			for (int i = 0; i < size; i++)
				if (get(i).equals(element)) {
					elements[i] = null;
					Object[] newElements = new Object[size - 1];
					int k = 0;
					for (int j = 0; j < size; j++) {
						if (get(j) != null)
							newElements[k++] = get(j);
					}
					size--;
					elements = newElements;
					return true;
				}
		return false;
	}

	public int capacity() {
		return elements.length;
	}

	public void set(int i, Object value) {
		if (readOnly)/*  조건문 안에 오독하기 쉬운 !표현 제거했다*/
			return ;
		if (i >= size)
			throw new ArrayIndexOutOfBoundsException();
		elements[i] = value;

	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}
}
