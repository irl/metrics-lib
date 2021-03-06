/* Copyright 2014--2018 The Tor Project
 * See LICENSE for licensing information */

package org.torproject.descriptor;

import java.util.List;

/**
 * Contains a relay microdescriptor.
 *
 * <p>A microdescriptor is a stripped-down version of a relay server
 * descriptor ({@link RelayServerDescriptor}) generated by the directory
 * authorities by extracting and/or transforming relay server descriptor
 * contents following strict rules without adding the authority's opinion
 * about the relay.  Microdescriptors are referenced from microdescriptor
 * consensuses ({@link RelayNetworkStatusConsensus}) and downloaded by
 * clients to make path-selection decisions and to build circuits.
 * Microdescriptors contain only the most relevant parts that clients care
 * about.  Microdescriptors are expected to be relatively static and only
 * change about once per week.</p>
 *
 * @since 1.0.0
 */
public interface Microdescriptor extends Descriptor {

  /**
   * Return the SHA-256 descriptor digest, encoded as 43 base64
   * characters without padding characters, that is used to reference this
   * descriptor from a vote or microdescriptor consensus.
   *
   * @since 1.7.0
   */
  String getDigestSha256Base64();

  /**
   * Return the RSA-1024 public key in PEM format used to encrypt CREATE
   * cells for this server, or null if the descriptor doesn't contain an
   * onion key.
   *
   * @since 1.0.0
   */
  String getOnionKey();

  /**
   * Return the curve25519 public key, encoded as 43 base64 characters
   * without padding characters, that is used for the ntor circuit
   * extended handshake, or null if the descriptor didn't contain an
   * ntor-onion-key line.
   *
   * @since 1.0.0
   */
  String getNtorOnionKey();

  /**
   * Return IP addresses and TCP ports where this server accepts TLS
   * connections for the main OR protocol, or an empty list if the server
   * does not support additional addresses or ports; entries are given in
   * the order as they are listed in the descriptor; IPv4 addresses are
   * given in dotted-quad format, IPv6 addresses use the colon-separated
   * hexadecimal format surrounded by square brackets, and TCP ports are
   * separated from the IP address using a colon.
   *
   * @since 1.0.0
   */
  List<String> getOrAddresses();

  /**
   * Return nicknames, $-prefixed identity fingerprints, or tuples of the
   * format {@code $fingerprint=nickname} or {@code $fingerprint~nickname}
   * of servers contained in this server's family, or null if the
   * descriptor does not contain a family line.
   *
   * @since 1.0.0
   */
  List<String> getFamilyEntries();

  /**
   * Return the default policy, {@code "accept"} or {@code "reject"}, of
   * the IPv4 port summary, or null if the descriptor didn't contain an
   * IPv4 exit-policy summary line which is equivalent to rejecting all
   * streams to IPv4 targets.
   *
   * @since 1.0.0
   */
  String getDefaultPolicy();

  /**
   * Return the port list of the IPv4 exit-policy summary, or null if the
   * descriptor didn't contain an IPv4 exit-policy summary line which is
   * equivalent to rejecting all streams to IPv4 targets.
   *
   * @since 1.0.0
   */
  String getPortList();

  /**
   * Return the default policy, {@code "accept"} or {@code "reject"}, of
   * the IPv6 port summary, or null if the descriptor didn't contain an
   * IPv6 exit-policy summary line which is equivalent to rejecting all
   * streams to IPv6 targets.
   *
   * @since 1.0.0
   */
  String getIpv6DefaultPolicy();

  /**
   * Return the port list of the IPv6 exit-policy summary, or null if the
   * descriptor didn't contain an IPv6 exit-policy summary line which is
   * equivalent to rejecting all streams to IPv6 targets.
   *
   * @since 1.0.0
   */
  String getIpv6PortList();

  /**
   * Return a SHA-1 digest of the server's RSA-1024 identity key, encoded
   * as 27 base64 characters without padding characters, that is only
   * included to prevent collisions between microdescriptors, or null if
   * no such digest is included.
   *
   * @since 1.1.0
   */
  String getRsa1024Identity();

  /**
   * Return a SHA-256 digest of the server's Ed25519 identity key,
   * encoded as 43 base64 characters without padding characters, that is
   * only included to prevent collisions between microdescriptors, or null
   * if no such digest is included.
   *
   * @since 1.1.0
   */
  String getEd25519Identity();
}

