/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Set;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public final class StatusCode {

    private static final long SEVERITY_MASK = 0xC0000000L;
    private static final long SEVERITY_GOOD = 0x00000000L;
    private static final long SEVERITY_UNCERTAIN = 0x40000000L;
    private static final long SEVERITY_BAD = 0x80000000L;

    /**
     * StatusCodes that are considered security-related errors for the purpose of diagnostics.
     */
    private static final Set<Long> SECURITY_ERRORS = Set.of(
        StatusCodes.Bad_UserSignatureInvalid,
        StatusCodes.Bad_UserAccessDenied,
        StatusCodes.Bad_SecurityPolicyRejected,
        StatusCodes.Bad_SecurityModeRejected,
        StatusCodes.Bad_SecurityChecksFailed,
        StatusCodes.Bad_SecureChannelTokenUnknown,
        StatusCodes.Bad_SecureChannelIdInvalid,
        StatusCodes.Bad_NoValidCertificates,
        StatusCodes.Bad_IdentityTokenInvalid,
        StatusCodes.Bad_IdentityTokenRejected,
        StatusCodes.Bad_IdentityChangeNotSupported,
        StatusCodes.Bad_CertificateUseNotAllowed,
        StatusCodes.Bad_CertificateUriInvalid,
        StatusCodes.Bad_CertificateUntrusted,
        StatusCodes.Bad_CertificateTimeInvalid,
        StatusCodes.Bad_CertificateRevoked,
        StatusCodes.Bad_CertificateRevocationUnknown,
        StatusCodes.Bad_CertificateIssuerUseNotAllowed,
        StatusCodes.Bad_CertificateIssuerTimeInvalid,
        StatusCodes.Bad_CertificateIssuerRevoked,
        StatusCodes.Bad_CertificateIssuerRevocationUnknown,
        StatusCodes.Bad_CertificateInvalid,
        StatusCodes.Bad_CertificateHostNameInvalid,
        StatusCodes.Bad_ApplicationSignatureInvalid
    );

    public static final StatusCode GOOD = new StatusCode(SEVERITY_GOOD);
    public static final StatusCode BAD = new StatusCode(SEVERITY_BAD);
    public static final StatusCode UNCERTAIN = new StatusCode(SEVERITY_UNCERTAIN);

    private final long value;

    public StatusCode(int value) {
        this.value = value & 0xFFFFFFFFL;
    }

    public StatusCode(long value) {
        this.value = value & 0xFFFFFFFFL;
    }

    public StatusCode(UInteger value) {
        this(value.longValue());
    }

    public long getValue() {
        return value;
    }

    public boolean isGood() {
        return (value & SEVERITY_MASK) == SEVERITY_GOOD;
    }

    public boolean isBad() {
        return (value & SEVERITY_MASK) == SEVERITY_BAD;
    }

    public boolean isUncertain() {
        return (value & SEVERITY_MASK) == SEVERITY_UNCERTAIN;
    }

    /**
     * Set the DataValue InfoType and Overflow InfoBits.
     *
     * @return a new {@link StatusCode} DataValue and Overflow bits set.
     */
    public StatusCode withOverflow() {
        return new StatusCode(value | 0x480);
    }

    /**
     * Clear the DataValue InfoType and Overflow InfoBits.
     *
     * @return a new {@link StatusCode} with DataValue and Overflow bits cleared.
     */
    public StatusCode withoutOverflow() {
        return new StatusCode(value & ~0x480);
    }

    /**
     * @return {@code true} if DataValue and Overflow bits are set.
     */
    public boolean isOverflowSet() {
        return (value & 0x480) == 0x480;
    }

    /**
     * @return {@code true} if the StatusCode is considered security-related for the purpose of diagnostics.
     */
    public boolean isSecurityError() {
        return SECURITY_ERRORS.contains(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusCode that = (StatusCode) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }

    @Override
    public String toString() {
        ToStringHelper helper = MoreObjects.toStringHelper(this);

        StatusCodes.lookup(value).ifPresent(
            nameAndDesc -> helper.add("name", nameAndDesc[0])
        );

        helper.add("value", String.format("0x%08X", value));
        helper.add("quality", quality(this));

        return helper.toString();
    }

    private static String quality(StatusCode statusCode) {
        if (statusCode.isGood()) {
            return "good";
        } else if (statusCode.isBad()) {
            return "bad";
        } else if (statusCode.isUncertain()) {
            return "uncertain";
        } else {
            return "unknown";
        }
    }

}
