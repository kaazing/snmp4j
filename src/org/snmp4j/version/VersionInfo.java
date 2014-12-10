/*_############################################################################
  _##
  _##  SNMP4J - VersionInfo.java
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

package org.snmp4j.version;

/**
 * The <code>VersionInfo</code> object returns information about the version
 * of this SNMP4J release.
 *
 * @author Frank Fock
 * @version 1.9.2
 * @since 1.9.1e
 */
public class VersionInfo {

  public static final int MAJOR = 1;
  public static final int MINOR = 11;
  public static final int UPDATE = 5;
  public static final String PATCH = "";

  public static final String VERSION =
      MAJOR + "." + MINOR + "." + UPDATE + PATCH;

  /**
   * Gets the version string for this release.
   * @return
   *    a string of the form <code>major.minor.update[patch]</code>.
   */
  public static final String getVersion() {
    return VERSION;
  }

  private VersionInfo() {
  }

}
