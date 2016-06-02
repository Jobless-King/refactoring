package com.nts.cleancode.collections;

public class Set extends AbstractCollection{/* 문제제기 : 필요없는 추상클래스를 제거 하려 한다. 해결방법 : 상위 클래스와의 의존성을 제거*/

	public void add(Object element){

		if (contains(element))
			return;
		super.add(element);
	}
	public void addAll(List l) {
		for (int i = 0; i < l.size(); i++) {
			if (!contains(l.get(i)))
				addElement(l.get(i));
		}
	}

}
