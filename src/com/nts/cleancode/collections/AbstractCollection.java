package com.nts.cleancode.collections;

public abstract class AbstractCollection  {
	protected Object[] elements = new Object[10];/*문제제기 : interface를 사용하는 부분이 해당 클래스 밖에 없다 . 해결 : 인터페이스를 삭제하고 추상 메소드를 내부에 선언*/

	/*collection interface의 값을 가지고 옮*/
	abstract  protected boolean isEmpty();
	abstract  protected void add(Object element);
	abstract  protected boolean remove(Object element);
	abstract  protected boolean contains(Object element);
	abstract  protected int size();

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

	public void add(Object key, Object value) {
	}

	public Object get(int i) {
		return elements[i];
	}
}
