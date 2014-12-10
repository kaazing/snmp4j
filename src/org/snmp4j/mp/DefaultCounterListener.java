/*_############################################################################
  _## 
  _##  SNMP4J - DefaultCounterListener.java  
  _## 
  _##  Copyright (C) 2003-2009  Frank Fock and Jochen Katz (SNMP4J.org)
  _##  
  _##  Licensed under the Apache License, Version 2.0 (the "License");
  _##  you may not use this file except in compliance with the License.
  _##  You may obtain a copy of the License at
  _##  
  _##      http://www.apache.org/licenses/LICENSE-2.0
  _##  
  _##  Unless required by applicable law or agreed to in writing, software
  _##  distributed under the License is distributed on an "AS IS" BASIS,
  _##  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  _##  See the License for the specific language governing permissions and
  _##  limitations under the License.
  _##  
  _##########################################################################*/





package org.snmp4j.mp;

import org.snmp4j.event.CounterListener;
import org.snmp4j.event.CounterEvent;
import java.util.Hashtable;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.Variable;

/**
 * The <code>DefaultCounterListener</code> is the default implementation of
 * the <code>CounterListener</code> interface. For any counter incrementation
 * event it checks whether the referenced counter object already exists. If not,
 * it will be created and initialized with one. Otherwise, the current value
 * will be incremented by one. In either case, the current value will be
 * returned in the event object.
 * <p>
 * To use a <code>DefaultCounterListener</code> with SNMP4J, add it to the
 * default <code>CounterSupport</code> by:
 * <pre>
 *   CounterSupport.getInstance().addCounterListener(new DefaultCounterListener());
 * </pre>
 *
 * @author Frank Fock
 * @version 1.0.1
 */
public class DefaultCounterListener implements CounterListener {

  private Hashtable counters = new Hashtable(50);

  /**
   * Default constructor.
   */
  public DefaultCounterListener() {
  }

  public synchronized void incrementCounter(CounterEvent event) {
    OID id = event.getOid();
    VariableBinding counter = (VariableBinding) counters.get(id);
    if (counter == null) {
      counter = new VariableBinding(id, new Counter32(1));
      counters.put(id, counter);
    }
    else {
      ((Counter32)counter.getVariable()).increment();
    }
    // write back current value
    event.setCurrentValue((Variable)
                          ((VariableBinding)counter).getVariable().clone());
  }
}
