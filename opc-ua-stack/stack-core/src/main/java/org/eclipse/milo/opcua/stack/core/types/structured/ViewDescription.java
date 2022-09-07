package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.45">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.45</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ViewDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=511");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=513");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=512");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15179");

    private final NodeId viewId;

    private final DateTime timestamp;

    private final UInteger viewVersion;

    public ViewDescription(NodeId viewId, DateTime timestamp, UInteger viewVersion) {
        this.viewId = viewId;
        this.timestamp = timestamp;
        this.viewVersion = viewVersion;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public NodeId getViewId() {
        return viewId;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public UInteger getViewVersion() {
        return viewVersion;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 513),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ViewId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("Timestamp", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("ViewVersion", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ViewDescription> {
        @Override
        public Class<ViewDescription> getType() {
            return ViewDescription.class;
        }

        @Override
        public ViewDescription decode(SerializationContext context, UaDecoder decoder) {
            NodeId viewId = decoder.readNodeId("ViewId");
            DateTime timestamp = decoder.readDateTime("Timestamp");
            UInteger viewVersion = decoder.readUInt32("ViewVersion");
            return new ViewDescription(viewId, timestamp, viewVersion);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ViewDescription value) {
            encoder.writeNodeId("ViewId", value.getViewId());
            encoder.writeDateTime("Timestamp", value.getTimestamp());
            encoder.writeUInt32("ViewVersion", value.getViewVersion());
        }
    }
}
