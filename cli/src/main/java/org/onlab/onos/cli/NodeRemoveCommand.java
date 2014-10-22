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
package org.onlab.onos.cli;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.onlab.onos.cluster.ClusterAdminService;
import org.onlab.onos.cluster.NodeId;

/**
 * Removes a controller cluster node.
 */
@Command(scope = "onos", name = "remove-node",
         description = "Removes a new controller cluster node")
public class NodeRemoveCommand extends AbstractShellCommand {

    @Argument(index = 0, name = "nodeId", description = "Node ID",
              required = true, multiValued = false)
    String nodeId = null;

    @Override
    protected void execute() {
        ClusterAdminService service = get(ClusterAdminService.class);
        service.removeNode(new NodeId(nodeId));
    }

}
