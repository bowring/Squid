/*
 * ReportListItemI.java
 *
 * Created on September 9, 2008, 3:05 PM
 *
 *
 * Copyright 2006-2018 James F. Bowring, CIRDLES.org, and Earth-Time.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.cirdles.squid.reports.reportViews;

/**
 * @author James F. Bowring
 */
public interface ReportListItemI {

    /**
     * @return
     */
    String getDisplayName();

    /**
     * @return
     */
    boolean isVisible();

    /**
     * @param visible
     */
    void setVisible(boolean visible);

    /**
     *
     */
    void ToggleIsVisible();

    /**
     * @param positionIndex
     */
    void setPositionIndex(int positionIndex);

}