/*_############################################################################
  _## 
  _##  SNMP4J - Target.java  
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

package org.snmp4j;

import java.io.Serializable;
import org.snmp4j.smi.Address;
// for JavaDoc
import org.snmp4j.mp.SnmpConstants;

/**
 * A <code>Target</code> interface defines an abstract representation of a
 * remote SNMP entity. It represents a target with an Address object, as well
 * protocol parameters such as retransmission and timeout policy.
 *
 * @author Frank Fock
 * @version 1.6
 */
public interface Target extends Serializable, Cloneable {

  /**
   * Gets the address of this target.
   * @return
   *    an Address instance.
   */
  Address getAddress();

  /**
   * Sets the address of the target.
   * @param address
   *    an Address instance.
   */
  void setAddress(Address address);

  /**
   * Sets the SNMP version (thus the SNMP messagen processing model) of the
   * target.
   * @param version
   *    the message processing model ID.
   * @see org.snmp4j.mp.SnmpConstants#version1
   * @see org.snmp4j.mp.SnmpConstants#version2c
   * @see org.snmp4j.mp.SnmpConstants#version3
   */
  void setVersion(int version);

  /**
   * Gets the SNMP version (NMP messagen processing model) of the target.
   * @return
   *    the message processing model ID.
   * @see org.snmp4j.mp.SnmpConstants#version1
   * @see org.snmp4j.mp.SnmpConstants#version2c
   * @see org.snmp4j.mp.SnmpConstants#version3
   */
  int getVersion();

  /**
   * Sets the number of retries to be performed before a request is timed out.
   * @param retries
   *    the number of retries. <em>Note: If the number of retries is set to
   *    0, then the request will be sent out exactly once.</em>
   */
  void setRetries(int retries);

  /**
   * Gets the number of retries.
   * @return
   *    an integer >= 0.
   */
  int getRetries();

  /**
   * Sets the timeout for a target.
   * @param timeout
   *    timeout in milliseconds before a confirmed request is resent or
   *    timed out.
   */
  void setTimeout(long timeout);

  /**
   * Gets the timeout for a target.
   * @return
   *    the timeout in milliseconds.
   */
  long getTimeout();

  /**
   * Gets the maxmim size of request PDUs that this target is able to respond
   * to. The default is 65535.
   * @return
   *    the maximum PDU size of request PDUs for this target. Which is always
   *    greater than 484.
   */
  int getMaxSizeRequestPDU();

  /**
   * Sets the maximum size of request PDUs that this target is able to receive.
   * @param maxSizeRequestPDU
   *    the maximum PDU (SNMP message) size this session will be able to
   *    process.
   */
  void setMaxSizeRequestPDU(int maxSizeRequestPDU);

  Object clone();
}

