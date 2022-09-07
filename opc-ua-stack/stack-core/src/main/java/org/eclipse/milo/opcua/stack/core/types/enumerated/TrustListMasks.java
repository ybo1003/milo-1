package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

public enum TrustListMasks implements UaEnumeration {
    None(0),

    TrustedCertificates(1),

    TrustedCrls(2),

    IssuerCertificates(4),

    IssuerCrls(8),

    All(15);

    private final int value;

    TrustListMasks(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=12552");
    }

    public static @Nullable TrustListMasks from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return TrustedCertificates;
            case 2:
                return TrustedCrls;
            case 4:
                return IssuerCertificates;
            case 8:
                return IssuerCrls;
            case 15:
                return All;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "None"),
            new EnumField(1L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "TrustedCertificates"),
            new EnumField(2L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "TrustedCrls"),
            new EnumField(4L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "IssuerCertificates"),
            new EnumField(8L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "IssuerCrls"),
            new EnumField(15L, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE, "All")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<TrustListMasks> {
        @Override
        public Class<TrustListMasks> getType() {
            return TrustListMasks.class;
        }

        @Override
        public TrustListMasks decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, TrustListMasks.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TrustListMasks value) {
            encoder.writeEnum(null, value);
        }
    }
}
