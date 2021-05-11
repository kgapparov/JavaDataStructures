import java.util.*;
public class BinaryHeap {
	public List<Integer> values;
	
	BinaryHeap(){
		values = new ArrayList<>();	
	};	
	private int getParent(int index) {
		return (int)Math.floor((2*index-1)/2);
	}

	public void insert(int value){
		values.add(value);
		bubleUp();
	}
	private void swap (int i, int j) {
		int tmp = values.get(j);
		values.set(j, values.get(i));
		values.set(i, tmp);
	} 

	private void bubleUp(){	
		int index = values.size() - 1; 
		int parentIndex = getParent(index);
		while (values.get(parentIndex) < values.get(index)) {
			swap(parentIndex, index);	
			index = parentIndex;
			parentIndex = getParent(index);
		}
	}

	public Integer extractMax(){
		int element;
		try {
			element = values.get(0);
		}catch(Exception e){
			System.out.println("No more Elements");	
			return null;
		}
		int length = values.size();
		if (length > 0 ) {
			values.set(0, values.get(length-1));
			print();
			values.remove(values.size()-1);
			sinkDown();
		}
		return element;	
	}
	
	private void sinkDown(){
		int index = 0;
		while (true) {
			Integer swap = null; 
			int leftChildIndex = index*2 + 1;
			int rightChildIndex = index*2 + 2;
			Integer childIndex = getMaxIndex(values, leftChildIndex, rightChildIndex);
			if (childIndex == null) {
				return;
			}
			if (values.get(index) < values.get(childIndex)) {
				swap = childIndex;
			}
			if (swap == null) {
				return;
			}
			swap(index, swap);
			index = swap;
		}		
	}

	private Integer  getMaxIndex(List<Integer> list, int i, int j) {
		if (i > list.size()-1) { 
			return null;	
		} 
		if (j > list.size()-1) {
			return i;
		}
		return (list.get(i) > list.get(j)) ? i : j;
	}
	
	public void print() {
		for (Integer number : values) {
			System.out.print(number+" ");
		}
		System.out.println();
	}
	public static void main(String... args){
		Random random = new Random();
		BinaryHeap bh = new BinaryHeap();

		for (int i = 0; i < 20; i++){
			int number = random.nextInt(101);
			bh.insert(number);					
		}
		bh.print();
	
		System.out.println();	
		int size = bh.values.size();
		for (int i = 0 ; i < size+1; i++) {
			System.out.printf("Max extraction # %d and value is %d\n", i , bh.extractMax());
			bh.print();
		}
	}
}
