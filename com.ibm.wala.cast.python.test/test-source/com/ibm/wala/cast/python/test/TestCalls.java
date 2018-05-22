package com.ibm.wala.cast.python.test;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

import com.ibm.wala.cast.ipa.callgraph.CAstCallGraphUtil;
import com.ibm.wala.cast.python.client.PythonAnalysisEngine;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.propagation.PropagationCallGraphBuilder;
import com.ibm.wala.ipa.callgraph.propagation.SSAContextInterpreter;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.util.CancelException;

public class TestCalls extends TestPythonCallGraphShape {

	 protected static final Object[][] assertionsCalls1 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls1.py" } },
		    new Object[] {
		        "script calls1.py",
		        new String[] { "Foo/foo", "foo", "$Foo/foo:trampoline3", "id", "nothing" } },
		    new Object[] {
		    	"$Foo/foo:trampoline3",
		    	new String[] { "Foo/foo" } },
		    new Object[] {
			    "call",
			    new String[] { "id" } },
		    new Object[] {
		    	"Foo/foo",
		    	new String[] { "id" } },
		    new Object[] {
		    	"foo",
			    new String[] { "call" } }
	 };
	 
	@Test
	public void testCalls1() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls1.py");
		verifyGraphAssertions(CG, assertionsCalls1);
	}

	 protected static final Object[][] assertionsCalls2 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls2.py" } },
		    new Object[] {
		        "script calls2.py",
		        new String[] { "Foo/foo", "foo", "$Foo/foo:trampoline3" } },
		    new Object[] {
		    	"$Foo/foo:trampoline3",
		    	new String[] { "Foo/foo" } },
		    new Object[] {
			    "call",
			    new String[] { "id" } },
		    new Object[] {
		    	"foo",
			    new String[] { "call" } }
	 };
	 
	@Test
	public void testCalls2() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls2.py");
		verifyGraphAssertions(CG, assertionsCalls2);
	}
	
	 protected static final Object[][] assertionsCalls3 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls3.py" } },
		    new Object[] {
		        "script calls3.py",
		        new String[] { "nothing", "id", "foo" } },
		    new Object[] {
			    "call",
			    new String[] { "id" } },
		    new Object[] {
		    	"foo",
			    new String[] { "call" } }
	 };

	@Test
	public void testCalls3() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls3.py");
		verifyGraphAssertions(CG, assertionsCalls3);
	}
	
	 protected static final Object[][] assertionsCalls4 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls4.py" } },
		    new Object[] {
		        "script calls4.py",
		        new String[] { "bad", "id", "foo" } }
	 };

	 @Test
	public void testCalls4() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls4.py");
		verifyGraphAssertions(CG, assertionsCalls4);
	}
	
	 protected static final Object[][] assertionsCalls5 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls5.py" } },
		    new Object[] {
		        "script calls5.py",
		        new String[] { "Foo", "$Foo/foo:trampoline3", "bad" } },
		    new Object[] {
		    	"$Foo/foo:trampoline3",
		    	new String[] { "Foo/foo" } },
		    new Object[] {
		    	"Foo/foo",
		    	new String[] { "id" } }
	 };

	 @Test
	public void testCalls5() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls5.py");
		verifyGraphAssertions(CG, assertionsCalls5);
	}
	
	 protected static final Object[][] assertionsCalls6 = new Object[][] {
		    new Object[] { ROOT, new String[] { "script calls6.py" } },
		    new Object[] {
		        "script calls6.py",
		        new String[] { "Foo", "$Foo/foo:trampoline3", "bad" } },
		    new Object[] {
		    	"$Foo/foo:trampoline3",
		    	new String[] { "Foo/foo" } },
		    new Object[] {
		    	"Foo/foo",
		    	new String[] { "id" } }
	 };

	 @Test
	public void testCalls6() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
		CallGraph CG = process("calls6.py");
		verifyGraphAssertions(CG, assertionsCalls6);
	}

	 @Test
	public void testCalls7() throws ClassHierarchyException, IllegalArgumentException, CancelException, IOException {
			PythonAnalysisEngine<?> e = new PythonAnalysisEngine<Void>() {
				@Override
				public Void performAnalysis(PropagationCallGraphBuilder builder) throws CancelException {
					assert false;
					return null;
				}
			};
			e.setModuleFiles(Collections.singleton(getScript("calls7.py")));
			PropagationCallGraphBuilder cgBuilder = (PropagationCallGraphBuilder) e.defaultCallGraphBuilder();
			CallGraph CG = cgBuilder.getCallGraph();	
			CAstCallGraphUtil.AVOID_DUMP = false;
			CAstCallGraphUtil.dumpCG((SSAContextInterpreter)cgBuilder.getContextInterpreter(), cgBuilder.getPointerAnalysis(), CG);
	}

}