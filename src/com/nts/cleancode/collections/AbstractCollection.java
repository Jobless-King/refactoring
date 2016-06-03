package com.nts.cleancode.collections;

public abstract class AbstractCollection  {/*문제제기 : interface를 사용하는 부분이 해당 클래스 밖에 없다 . 해결 : 인터페이스를 삭제하고 추상 메소드를 내부에 선언*/
	private static int INITIAL_CAPACITY = 10;
	protected int size = 0;
	protected Object[] elements = new Object[INITIAL_CAPACITY];
	protected boolean readOnly;

	public boolean isEmpty() {
		return size == 0;
	}

	/* 문제 제기 : 메소드의 가독성을 높였다. 해결 : 바로 파악할 수 있도록 처리했다 */
	public void add(Object element) {
		if (readOnly) /*  조건문 안에 오독하기 쉬운 !표현 제거했다. */
		return;
		if (shouldGrow()) { /* 문제 제기 : if문의 가독성이 떨어진다. 해결 : inline, Extract method*/
			grow();/* 문제 제기 : 한눈에 의도를 파악하기 어렵다 . 해결 : Extract method */
		}
		addElement(element);/*문제제기 : 한눈에 의도를 파악하기 어렵다. 해결 : Extract method */
	}

	public int size() {
		return size;
	}

	public boolean remove(Object element) {
		if (readOnly)
			return false;
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element)) {
				removeElementAt(i);
				return true;
			}
		return false;
	}

	public void removeElementAt(int i) {
		elements[i] = null;
		Object[] newElements = new Object[size - 1];
		int k = 0;
		for (int j = 0; j < size; j++) {
            if (elements[j] != null)
                newElements[k++] = elements[j];
        }
		size--;
		elements = newElements;
	}


	void addElement(Object element) {
		elements[size++] = element;
	}


	void grow() {
		Object[] newElements =
            new Object[elements.length + 10];
		for (int i = 0; i < size; i++)
            newElements[i] = get(i);
		elements = newElements;
	}



	boolean shouldGrow() {
		return size + 1 > elements.length;
	}

	public boolean contains(Object element) {
		for (int i = 0; i < size; i++)
			if (elements[i].equals(element))
				return true;
		return false;
	}

	/*문제제기 : 비슷한 코드가 반복되어 묶어서 코드의 중복을 방지한다.  해결 : 내부 구조를 맞추고 상위 AbstractCollection 객체로 선언 받게 했다.*/
	/*문제제기 : 비슷한 코드가 반복되어 묶어서 코드의 중복을 방지한다.  해결 : 선형구조인 List와 set와 다른 메소드를 호출하는 map instance를 바꾼다.*/

	public void addAll(AbstractCollection collection) { /* 변수명을 의미 있게 변경 */
		/* 같은 역할을 하는 함수 get으로 통일 */

		for (int i = 0; i < collection.size(); i++) {
			if (!contains(collection.get(i))) { /* 같은 역할을 하는 함수 get으로 통일 */
				add(collection.get(i));
			}
		}
	}
	/* Map 과의 연관성이 있던 add 삭제*/


	public Object get(int index) {
		return elements[index];
	}

	public int capacity() {
		return elements.length;
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}
}
