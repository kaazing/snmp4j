/*_############################################################################
  _## 
  _##  SNMP4J - TransportMappings.java  
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

package org.snmp4j.transport;

import org.snmp4j.smi.Address;
import org.snmp4j.TransportMapping;
import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Enumeration;
import org.snmp4j.log.*;
import java.lang.reflect.Constructor;
import org.snmp4j.SNMP4JSettings;
import java.lang.reflect.InvocationTargetException;

/**
 * The <code>TransportMappings</code> factory can be used to create a transport
 * mapping for an address class.
 *
 * @author Frank Fock
 * @version 1.1
 * @since 1.1
 */
public class TransportMappings {

  private static final LogAdapter logger = LogFactory.getLogger(TransportMappings.class);

  public static final String TRANSPORT_MAPPINGS =
      "org.snmp4j.transportMappings";
  private static final String TRANSPORT_MAPPINGS_DEFAULT =
      "transports.properties";

  private static TransportMappings instance = null;
  private Hashtable transportMappings = null;

  protected TransportMappings() {
  }

  /**
   * Returns the <code>TransportMappings</code> singleton.
   * @return
   *    the <code>TransportMappings</code> instance.
   */
  public static TransportMappings getInstance() {
    if (instance == null) {
      instance = new TransportMappings();
    }
    return instance;
  }

  /**
   * Returns a <code>TransportMapping</code> instance that is initialized with
   * the supplied transport address.
   * If no such mapping exists, <code>null</code> is returned. To register
   * third party transport mappings, please set the system property
   * {@link #TRANSPORT_MAPPINGS} to a transport mappings registration file,
   * before calling this method for the first time.
   *
   * @param transportAddress
   *   an <code>Address</code> instance that the transport mapping to lookup
   *   has to support.
   * @return
   *   a <code>TransportMapping</code> that supports the specified
   *   <code>transportAddress</code> or <code>null</code> if such a mapping
   *   cannot be found.
   */
  public TransportMapping createTransportMapping(Address transportAddress) {
    if (transportMappings == null) {
      registerTransportMappings();
    }
    Class c =
        (Class) transportMappings.get(transportAddress.getClass().getName());
    if (c == null) {
      return null;
    }
    Class[] params = new Class[1];
    params[0] = transportAddress.getClass();
    Constructor constructor = null;
    try {
      constructor = c.getConstructor(params);
      return (TransportMapping)
          constructor.newInstance(new Object[] { transportAddress });
    }
    catch (InvocationTargetException ite) {
      if (logger.isDebugEnabled()) {
        ite.printStackTrace();
      }
      logger.error(ite);
      throw new RuntimeException(ite.getTargetException());
    }
    catch (Exception ex) {
      if (logger.isDebugEnabled()) {
        ex.printStackTrace();
      }
      logger.error(ex);
      return null;
    }
  }

  protected synchronized void registerTransportMappings() {
    if (SNMP4JSettings.isExtensibilityEnabled()) {
      String transports =
          System.getProperty(TRANSPORT_MAPPINGS, TRANSPORT_MAPPINGS_DEFAULT);
      InputStream is = TransportMappings.class.getResourceAsStream(transports);
      if (is == null) {
        throw new InternalError("Could not read '" + transports +
                                "' from classpath!");
      }
      Properties props = new Properties();
      try {
        props.load(is);
        Hashtable t = new Hashtable(props.size());
        for (Enumeration en = props.propertyNames(); en.hasMoreElements(); ) {
          String addressClassName = (String) en.nextElement();
          String className = props.getProperty(addressClassName);
          try {
            Class c = Class.forName(className);
            t.put(addressClassName, c);
          }
          catch (ClassNotFoundException cnfe) {
            logger.error(cnfe);
          }
        }
        // atomic syntax registration
        transportMappings = t;
      }
      catch (IOException iox) {
        String txt = "Could not read '" + transports + "': " +
            iox.getMessage();
        logger.error(txt);
        throw new InternalError(txt);
      }
      finally {
        try {
          is.close();
        }
        catch (IOException ex) {
          logger.warn(ex);
        }
      }
    }
    else {
      Hashtable t = new Hashtable(2);
      t.put("org.snmp4j.smi.UdpAddress", DefaultUdpTransportMapping.class);
      t.put("org.snmp4j.smi.TcpAddress", DefaultTcpTransportMapping.class);
      transportMappings = t;
    }
  }

}
