package com.nts.cleancode.collections;

public abstract class AbstractCollection  {/*문제제기 : interface를 사용하는 부분이 해당 클래스 밖에 없다 . 해결 : 인터페이스를 삭제하고 추상 메소드를 내부에 선언*/

	/*collection interface의 값을 가지고 옮*/
	abstract  protected boolean isEmpty();
	abstract  protected void add(Object element);
	abstract  protected boolean remove(Object element);
	abstract  protected boolean contains(Object element);
	abstract  protected int size();


	public void addAll(AbstractCollection c) {
		if (c instanceof Set) {
			Set s = (Set)c;
			for (int i=0; i < s.size(); i++) {
				if (!contains(s.getElementAt(i))) {
					add(s.getElementAt(i));
				}
			}

		} else if (c instanceof List) {
			List l = (List)c;
			for (int i=0; i < l.size(); i++) {
				if (!contains(l.get(i))) {
					add(l.get(i));
				}
			}
		}else if (c instanceof Map) {
			Map m = (Map)c;
			for (int i=0; i<m.size(); i++)
				add(m.keys[i], m.values[i]);
		}
	}

	public void add(Object key, Object value) {
	}
}
