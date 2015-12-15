/* Copyright 2015 The Tor Project
 * See LICENSE for licensing information */
package org.torproject.descriptor.impl;

import java.util.ArrayList;
import java.util.List;

import org.torproject.descriptor.BridgeServerDescriptor;
import org.torproject.descriptor.DescriptorParseException;
import org.torproject.descriptor.ServerDescriptor;

public class BridgeServerDescriptorImpl extends ServerDescriptorImpl
    implements BridgeServerDescriptor {

  protected static List<ServerDescriptor> parseDescriptors(
      byte[] descriptorsBytes, boolean failUnrecognizedDescriptorLines)
      throws DescriptorParseException {
    List<ServerDescriptor> parsedDescriptors =
        new ArrayList<ServerDescriptor>();
    List<byte[]> splitDescriptorsBytes =
        DescriptorImpl.splitRawDescriptorBytes(descriptorsBytes,
        "router ");
    for (byte[] descriptorBytes : splitDescriptorsBytes) {
      ServerDescriptor parsedDescriptor =
          new BridgeServerDescriptorImpl(descriptorBytes,
          failUnrecognizedDescriptorLines);
      parsedDescriptors.add(parsedDescriptor);
    }
    return parsedDescriptors;
  }

  protected BridgeServerDescriptorImpl(byte[] descriptorBytes,
      boolean failUnrecognizedDescriptorLines)
      throws DescriptorParseException {
    super(descriptorBytes, failUnrecognizedDescriptorLines);
  }
}

