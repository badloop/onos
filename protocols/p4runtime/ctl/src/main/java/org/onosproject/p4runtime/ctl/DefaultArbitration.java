/*
 * Copyright 2017-present Open Networking Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.p4runtime.ctl;

import org.onosproject.net.DeviceId;
import org.onosproject.net.MastershipRole;
import org.onosproject.p4runtime.api.P4RuntimeEventSubject;
import p4.v1.P4RuntimeOuterClass.Uint128;

/**
 * Default implementation of arbitration in P4Runtime.
 */
public class DefaultArbitration implements P4RuntimeEventSubject {
    private MastershipRole role;
    private Uint128 electionId;
    private DeviceId deviceId;

    /**
     * Creates arbitration with given role and election id.
     *
     * @param deviceId   the device
     * @param role       the role
     * @param electionId the election id
     */
     DefaultArbitration(DeviceId deviceId, MastershipRole role, Uint128 electionId) {
        this.deviceId = deviceId;
        this.role = role;
        this.electionId = electionId;
    }

    /**
     * Gets the role of this arbitration.
     *
     * @return the role
     */
    public MastershipRole role() {
        return role;
    }

    /**
     * Gets election id of this arbitration.
     *
     * @return the election id
     */
    public Uint128 electionId() {
        return electionId;
    }

    @Override
    public DeviceId deviceId() {
        return deviceId;
    }
}
