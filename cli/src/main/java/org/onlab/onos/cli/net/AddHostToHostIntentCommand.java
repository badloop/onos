/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.onlab.onos.cli.net;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.onlab.onos.cli.AbstractShellCommand;
import org.onlab.onos.net.HostId;
import org.onlab.onos.net.flow.DefaultTrafficSelector;
import org.onlab.onos.net.flow.DefaultTrafficTreatment;
import org.onlab.onos.net.flow.TrafficSelector;
import org.onlab.onos.net.flow.TrafficTreatment;
import org.onlab.onos.net.intent.HostToHostIntent;
import org.onlab.onos.net.intent.IntentService;

/**
 * Installs host-to-host connectivity intent.
 */
@Command(scope = "onos", name = "add-host-intent",
         description = "Installs host-to-host connectivity intent")
public class AddHostToHostIntentCommand extends AbstractShellCommand {

    @Argument(index = 0, name = "one", description = "One host ID",
              required = true, multiValued = false)
    String one = null;

    @Argument(index = 1, name = "two", description = "Another host ID",
              required = true, multiValued = false)
    String two = null;

    @Override
    protected void execute() {
        IntentService service = get(IntentService.class);

        HostId oneId = HostId.hostId(one);
        HostId twoId = HostId.hostId(two);

        TrafficSelector selector = DefaultTrafficSelector.builder().build();
        TrafficTreatment treatment = DefaultTrafficTreatment.builder().build();

        HostToHostIntent intent = new HostToHostIntent(appId(), oneId, twoId,
                                                       selector, treatment);
        service.submit(intent);
    }

}
