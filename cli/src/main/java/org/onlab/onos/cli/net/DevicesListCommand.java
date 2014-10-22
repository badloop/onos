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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.karaf.shell.commands.Command;
import org.onlab.onos.cli.AbstractShellCommand;
import org.onlab.onos.cli.Comparators;
import org.onlab.onos.net.Device;
import org.onlab.onos.net.device.DeviceService;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Lists all infrastructure devices.
 */
@Command(scope = "onos", name = "devices",
         description = "Lists all infrastructure devices")
public class DevicesListCommand extends AbstractShellCommand {

    private static final String FMT =
            "id=%s, available=%s, role=%s, type=%s, mfr=%s, hw=%s, sw=%s, serial=%s";

    @Override
    protected void execute() {
        DeviceService service = get(DeviceService.class);
        if (outputJson()) {
            print("%s", json(service, getSortedDevices(service)));
        } else {
            for (Device device : getSortedDevices(service)) {
                printDevice(service, device);
            }
        }
    }

    /**
     * Returns JSON node representing the specified devices.
     *
     * @param service device service
     * @param devices collection of devices
     * @return JSON node
     */
    public static JsonNode json(DeviceService service, Iterable<Device> devices) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode result = mapper.createArrayNode();
        for (Device device : devices) {
            result.add(json(service, mapper, device));
        }
        return result;
    }

    /**
     * Returns JSON node representing the specified device.
     *
     * @param service device service
     * @param mapper  object mapper
     * @param device  infrastructure device
     * @return JSON node
     */
    public static ObjectNode json(DeviceService service, ObjectMapper mapper,
                                  Device device) {
        ObjectNode result = mapper.createObjectNode();
        if (device != null) {
            result.put("id", device.id().toString())
                    .put("available", service.isAvailable(device.id()))
                    .put("role", service.getRole(device.id()).toString())
                    .put("mfr", device.manufacturer())
                    .put("hw", device.hwVersion())
                    .put("sw", device.swVersion())
                    .put("serial", device.serialNumber());
        }
        return result;
    }

    /**
     * Returns the list of devices sorted using the device ID URIs.
     *
     * @param service device service
     * @return sorted device list
     */
    protected static List<Device> getSortedDevices(DeviceService service) {
        List<Device> devices = newArrayList(service.getDevices());
        Collections.sort(devices, Comparators.ELEMENT_COMPARATOR);
        return devices;
    }

    /**
     * Prints information about the specified device.
     *
     * @param service device service
     * @param device  infrastructure device
     */
    protected void printDevice(DeviceService service, Device device) {
        if (device != null) {
            print(FMT, device.id(), service.isAvailable(device.id()),
                  service.getRole(device.id()), device.type(),
                  device.manufacturer(), device.hwVersion(), device.swVersion(),
                  device.serialNumber());
        }
    }

}
