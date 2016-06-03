package com.nts.cleancode.collections;

public class Map  {
	private static int INITIAL_CAPACITY = 10;
	protected List keys = new List();/* 문제 제기 : 배열의 경우 크기가 정해져 있으므로 리스트를 통해 크기의 제한을 없앴다 */
	protected List values = new List();
	private boolean readOnly;

	/* 문제제기: size 멤버변수 제거로 인한 size 확인 불가. 해결 : List의 isEmpty() 메소드로 대체*/
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	// Do nothing because user must input key and value
	public void add(Object element) {
	}
	/* 문제 제기 : 메소드의 가독성을 높였다. 해결 : 바로 파악할 수 있도록 처리했다 */
	/* 문제 제기 : Object[]인 keys, values의 타입을 List를 변경하여 문제가 발생하였습니다.
	 * 해결 : Map에 key가 있을 경우, values를 Set으로 처리하며 key가 없을 경우, keys와 values를 add하였습니다.*/
	public void add(Object key, Object value) {
		if (readOnly) /*  조건문 안에 오독하기 쉬운 !표현 제거했다. */
			return;
		int indexOfKey = find(key);
		if (indexOfKey != -1) {
			values.set(indexOfKey,value);
		}else{
			keys.add(key);
			values.add(value);
		}

	}

	/* 문제 제기 : size 멤버변수 제거로 인한 오류 발생. 해결 : List의 size메소드로 이용 */
	public int size() {
		return keys.size();
	}

	/* 문제 제기 : if 문 내부의 코드가 List의 특정 메소드의 동작과 동일합니다. 해결 : List의 removeElementAt 메소드로 대체 */
	public boolean remove(Object key) {
		if (readOnly)
			return false;
		for (int i = 0; i < keys.size; i++)
			if (keys.get(i).equals(key)) {
				keys.removeElementAt(i);
				return true;
			}
		return false;
	}

	public boolean contains(Object value) {
		for (int i = 0; i < values.size; i++)
			if ((value == null && values.get(i) == null)
				|| (values.get(i) != null && values.get(i).equals(value)))
				return true;
		return false;
	}
	/* 문제 제기 : for문 코드의 중복을 제거하려고 한다. 해결방법 : find(Key)의 형태를 맞춰서 extract 처리했다. */
	public boolean containsKey(Object key) {
		return find(key) != -1;
	}
	/* 문제 제기 : for문 코드의 중복을 제거하고 가독성을 높이려한다. 해결방법 : contains의 코드를 중복하기 위해 묶어처리했다.*/
	private int find(Object key) {
		for (int i = 0; i < keys.size(); i++)
			if (keys.get(i) != null && keys.get(i).equals(key)) {
				return i;
			}
		return -1;
	}
	/* 문제 제기 : for문 코드의 중복을 제거하고 가독성을 높이려한다. 해결방법 : 기존의 for문을 find로 대체했다. */
	public Object get(Object key) {
		int indexOfKey = find(key);
		if (indexOfKey == -1)
			return null;
		return values.get(indexOfKey);
	}

	public int capacity() {
		return keys.capacity();
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}

	/* 상속받던 addAll 메소드를 내리고 상속관계를 제거했다.  */
	public void addAll(Map m) {
		for (int i=0; i<m.size(); i++)
			add(m.keys.get(i), m.values.get(i));
	}

}
