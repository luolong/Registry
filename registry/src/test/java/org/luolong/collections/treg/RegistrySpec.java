package org.luolong.collections.treg;

import jdave.Specification;
import jdave.junit4.JDaveRunner;

import org.junit.runner.RunWith;

@RunWith(JDaveRunner.class)
public class RegistrySpec extends Specification<Registry>{
	public class EmptyRegistry {
		Registry registry;
		
		public Registry create() {
			registry = new HashRegistry();
			return registry;
		}
		
		public void isEmpty(){
			specify(registry, must.be.isEmpty());
		}
		
		public void hasSizeOfZero() {
			specify( registry.size(), must.equal( 0 ) );
		}
		
		public void isNoLongerEmptyAfterPut() {
			registry.put(TypedKey.of( String.class ), "anything");
			specify( registry, should.not().be.isEmpty() );
		}
	}
}
