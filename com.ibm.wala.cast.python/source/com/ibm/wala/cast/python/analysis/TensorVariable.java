package com.ibm.wala.cast.python.analysis;

import java.util.Set;

import com.ibm.wala.cast.python.types.TensorType;
import com.ibm.wala.fixpoint.IVariable;
import com.ibm.wala.util.collections.HashSetFactory;

public class TensorVariable implements IVariable<TensorVariable> {
	private int graphNodeId = -1;
	private int orderNumber = -1;
	
	Set<TensorType> state = HashSetFactory.make();
	
	@Override
	public int getGraphNodeId() {
		return graphNodeId;
	}

	@Override
	public void setGraphNodeId(int number) {
		graphNodeId = number;
	}

	@Override
	public int getOrderNumber() {
		return orderNumber;
	}

	@Override
	public void setOrderNumber(int i) {
		orderNumber = i;
	}

	@Override
	public void copyState(TensorVariable v) {
		this.state = HashSetFactory.make(v.state);
	}

	@Override
	public String toString() {
		return "tensor types:" + state;
	}
}