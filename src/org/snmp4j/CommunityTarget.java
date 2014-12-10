/*_############################################################################
  _## 
  _##  SNMP4J - CommunityTarget.java  
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

import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Address;
import org.snmp4j.mp.SnmpConstants;

/**
 * A <code>CommunityTarget</code> represents SNMP target properties for
 * community based message processing models (SNMPv1 and SNMPv2c).
 * @author Frank Fock
 * @version 1.1
 */
public class CommunityTarget extends AbstractTarget {

  static final long serialVersionUID = 147443821594052003L;

  private org.snmp4j.smi.OctetString community = new OctetString();

  /**
   * Default constructor.
   */
  public CommunityTarget() {
    setVersion(SnmpConstants.version1);
  }

  /**
   * Creates a fully specified communtity target.
   * @param address
   *    the transport <code>Address</code> of the target.
   * @param community
   *    the community to be used for the target.
   */
  public CommunityTarget(Address address, OctetString community) {
    super(address);
    setVersion(SnmpConstants.version1);
    setCommunity(community);
  }

  /**
   * Gets the community octet string.
   * @return
   *    an <code>OctetString</code> instance, never <code>null</code>.
   */
  public OctetString getCommunity() {
    return community;
  }

  /**
   * Sets the community octet sting.
   * @param community
   *    an <code>OctetString</code> instance which must not be
   *    <code>null</code>.
   */
  public void setCommunity(OctetString community) {
    if (community == null) {
      throw new IllegalArgumentException("Community must not be null");
    }
    this.community = community;
  }

  public String toString() {
    return "CommunityTarget["+toStringAbstractTarget()+
        ", community="+community+"]";
  }

}
