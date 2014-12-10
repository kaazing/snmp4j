/*_############################################################################
  _## 
  _##  SNMP4J - SecurityLevel.java  
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





package org.snmp4j.security;

/**
 * The <code>SecurityLevel</code> interface contains enumerated values
 * for the different security levels.
 *
 * @author Frank Fock
 * @version 1.0
 */
public final class SecurityLevel {

  /**
   * No authentication and no encryption.
   * Anyone can create and read messages with this security level
   */
  public static final int NOAUTH_NOPRIV = 1;

  /**
   * Authentication and no encryption.
   * Only the one with the right authentication key can create messages
   * with this security level, but anyone can read the contents of
   * the message.
   */
  public static final int AUTH_NOPRIV = 2;

  /**
   * Authentication and encryption.
   * Only the one with the right authentication key can create messages
   * with this security level, and only the one with the right
   * encryption/decryption key can read the contents of the message.
   */
  public static final int AUTH_PRIV = 3;


}
