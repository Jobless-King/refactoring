package com.nts.cleancode.collections;

public class List extends AbstractCollection{ /* 문제제기 : 필요없는 추상클래스를 제거 하려 한다. 해결방법 : 상위 클래스와의 의존성을 제거*/


	public void set(int i, Object value) {
		if (readOnly)/*  조건문 안에 오독하기 쉬운 !표현 제거했다*/
			return ;
		if (i >= size)
			throw new ArrayIndexOutOfBoundsException();
		elements[i] = value;

	}
}
